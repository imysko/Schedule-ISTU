package com.istu.schedule.ui.components.toolbar

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun CustomToolbar(
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
    centralContent: (@Composable () -> Unit)? = null,
    additionalContent: (@Composable () -> Unit)? = null,
    collapsingTitle: CollapsingTitle? = null,
    scrollBehavior: CustomToolbarScrollBehavior? = null,
    collapsedElevation: Dp = _defaultCollapsedElevation
) {
    val collapsedFraction = when {
        scrollBehavior != null && centralContent == null -> scrollBehavior.state.collapsedFraction
        scrollBehavior != null && centralContent != null -> 0f
        else -> 1f
    }

    val fullyCollapsedTitleScale = when {
        collapsingTitle != null ->
            _collapsedTitleLineHeight.value / collapsingTitle.expandedTextStyle.lineHeight.value

        else -> 1f
    }

    val collapsingTitleScale = lerp(1f, fullyCollapsedTitleScale, collapsedFraction)

    val showElevation = when {
        scrollBehavior == null -> false
        scrollBehavior.state.contentOffset <= 0 && collapsedFraction == 1f -> true
        scrollBehavior.state.contentOffset < -1f && centralContent != null -> true
        else -> false
    }

    val elevationState = animateDpAsState(
        if (showElevation) collapsedElevation else 0.dp,
        label = ""
    )

    Surface(
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        shadowElevation = elevationState.value
    ) {
        Layout(
            content = {
                if (collapsingTitle != null) {
                    Text(
                        modifier = Modifier
                            .layoutId(ExpandedTitleId)
                            .wrapContentHeight(align = Alignment.Top)
                            .graphicsLayer(
                                scaleX = collapsingTitleScale,
                                scaleY = collapsingTitleScale,
                                transformOrigin = TransformOrigin(0f, 0f)
                            ),
                        text = collapsingTitle.titleText,
                        style = collapsingTitle.expandedTextStyle
                    )
                    Text(
                        modifier = Modifier
                            .layoutId(CollapsedTitleId)
                            .wrapContentHeight(align = Alignment.Top)
                            .graphicsLayer(
                                scaleX = collapsingTitleScale,
                                scaleY = collapsingTitleScale,
                                transformOrigin = TransformOrigin(0f, 0f)
                            ),
                        text = collapsingTitle.titleText,
                        style = collapsingTitle.expandedTextStyle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (navigationIcon != null) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .layoutId(NavigationIconId)
                    ) {
                        navigationIcon()
                    }
                }

                if (actions != null) {
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .layoutId(ActionsId)
                    ) {
                        actions()
                    }
                }

                if (centralContent != null) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .layoutId(CentralContentId)
                    ) {
                        centralContent()
                    }
                }

                if (additionalContent != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .layoutId(AdditionalContentId)
                    ) {
                        additionalContent()
                    }
                }
            },
            modifier = modifier.then(Modifier.heightIn(min = _minCollapsedHeight))
        ) { measurables, constraints ->
            val horizontalPaddingPx = _horizontalPadding.toPx()
            val expandedTitleBottomPaddingPx = _expandedTitleBottomPadding.toPx()

            // Measuring widgets inside toolbar:

            val navigationIconPlaceable = measurables.firstOrNull {
                it.layoutId == NavigationIconId
            }
                ?.measure(constraints.copy(minWidth = 0))

            val actionsPlaceable = measurables.firstOrNull { it.layoutId == ActionsId }
                ?.measure(constraints.copy(minWidth = 0))

            val expandedTitlePlaceable = measurables.firstOrNull { it.layoutId == ExpandedTitleId }
                ?.measure(
                    constraints.copy(
                        maxWidth = (constraints.maxWidth - 2 * horizontalPaddingPx).roundToInt(),
                        minWidth = 0,
                        minHeight = 0
                    )
                )

            val additionalContentPlaceable = measurables.firstOrNull {
                it.layoutId == AdditionalContentId
            }
                ?.measure(constraints)

            val navigationIconOffset = when (navigationIconPlaceable) {
                null -> horizontalPaddingPx
                else -> navigationIconPlaceable.width + horizontalPaddingPx * 2
            }

            val actionsOffset = when (actionsPlaceable) {
                null -> horizontalPaddingPx
                else -> actionsPlaceable.width + horizontalPaddingPx * 2
            }

            val collapsedTitleMaxWidthPx =
                (
                    constraints.maxWidth - navigationIconOffset -
                        actionsOffset
                    ) / fullyCollapsedTitleScale

            val collapsedTitlePlaceable = measurables.firstOrNull {
                it.layoutId == CollapsedTitleId
            }
                ?.measure(
                    constraints.copy(
                        maxWidth = collapsedTitleMaxWidthPx.roundToInt(),
                        minWidth = 0,
                        minHeight = 0
                    )
                )

            val centralContentPlaceable = measurables.firstOrNull {
                it.layoutId == CentralContentId
            }
                ?.measure(
                    constraints.copy(
                        minWidth = 0,
                        maxWidth = (constraints.maxWidth - navigationIconOffset - actionsOffset)
                            .roundToInt()
                    )
                )

            val collapsedHeightPx = when {
                centralContentPlaceable != null ->
                    max(_minCollapsedHeight.toPx(), centralContentPlaceable.height.toFloat())

                else -> _minCollapsedHeight.toPx()
            }

            var layoutHeightPx = collapsedHeightPx

            // Calculating coordinates of widgets inside toolbar:

            // Current coordinates of navigation icon
            val navigationIconX = horizontalPaddingPx.roundToInt()
            val navigationIconY =
                ((collapsedHeightPx - (navigationIconPlaceable?.height ?: 0)) / 2).roundToInt()

            // Current coordinates of actions
            val actionsX = (
                constraints.maxWidth - (
                    actionsPlaceable?.width
                        ?: 0
                    ) - horizontalPaddingPx
                ).roundToInt()
            val actionsY = ((collapsedHeightPx - (actionsPlaceable?.height ?: 0)) / 2).roundToInt()

            // Current coordinates of title
            var collapsingTitleY = 0
            var collapsingTitleX = 0

            if (expandedTitlePlaceable != null && collapsedTitlePlaceable != null) {
                // Measuring toolbar collapsing distance
                val heightOffsetLimitPx =
                    expandedTitlePlaceable.height + expandedTitleBottomPaddingPx
                scrollBehavior?.state?.heightOffsetLimit = when (centralContent) {
                    null -> -heightOffsetLimitPx
                    else -> -1f
                }

                // Toolbar height at fully expanded state
                val fullyExpandedHeightPx = _minCollapsedHeight.toPx() + heightOffsetLimitPx

                // Coordinates of fully expanded title
                val fullyExpandedTitleX = horizontalPaddingPx
                val fullyExpandedTitleY = fullyExpandedHeightPx -
                    expandedTitlePlaceable.height - expandedTitleBottomPaddingPx

                // Coordinates of fully collapsed title
                val fullyCollapsedTitleX = navigationIconOffset
                val fullyCollapsedTitleY =
                    collapsedHeightPx / 2 - _collapsedTitleLineHeight.toPx().roundToInt() / 2

                // Current height of toolbar
                layoutHeightPx = lerp(fullyExpandedHeightPx, collapsedHeightPx, collapsedFraction)

                // Current coordinates of collapsing title
                collapsingTitleX = lerp(
                    fullyExpandedTitleX,
                    fullyCollapsedTitleX,
                    collapsedFraction
                ).roundToInt()
                collapsingTitleY = lerp(
                    fullyExpandedTitleY,
                    fullyCollapsedTitleY,
                    collapsedFraction
                ).roundToInt()
            } else {
                scrollBehavior?.state?.heightOffsetLimit = -1f
            }

            val toolbarHeightPx =
                layoutHeightPx.roundToInt() + (additionalContentPlaceable?.height ?: 0)

            // Placing toolbar widgets:

            layout(constraints.maxWidth, toolbarHeightPx) {
                navigationIconPlaceable?.placeRelative(
                    x = navigationIconX,
                    y = navigationIconY
                )
                actionsPlaceable?.placeRelative(
                    x = actionsX,
                    y = actionsY
                )
                centralContentPlaceable?.placeRelative(
                    x = navigationIconOffset.roundToInt(),
                    y = ((collapsedHeightPx - centralContentPlaceable.height) / 2).roundToInt()
                )
                if (expandedTitlePlaceable?.width == collapsedTitlePlaceable?.width) {
                    expandedTitlePlaceable?.placeRelative(
                        x = collapsingTitleX,
                        y = collapsingTitleY
                    )
                } else {
                    expandedTitlePlaceable?.placeRelativeWithLayer(
                        x = collapsingTitleX,
                        y = collapsingTitleY,
                        layerBlock = { alpha = 1 - collapsedFraction }
                    )
                    collapsedTitlePlaceable?.placeRelativeWithLayer(
                        x = collapsingTitleX,
                        y = collapsingTitleY,
                        layerBlock = { alpha = collapsedFraction }
                    )
                }
                additionalContentPlaceable?.placeRelative(
                    x = 0,
                    y = layoutHeightPx.roundToInt()
                )
            }
        }
    }
}

private fun lerp(a: Float, b: Float, fraction: Float): Float {
    return a + fraction * (b - a)
}

data class CollapsingTitle(
    val titleText: String,
    val expandedTextStyle: TextStyle
) {

    companion object {
        @Composable
        fun large(titleText: String) = CollapsingTitle(
            titleText,
            MaterialTheme.typography.headlineLarge
        )

        @Composable
        fun medium(titleText: String) = CollapsingTitle(
            titleText,
            MaterialTheme.typography.headlineMedium
        )
    }
}

private val _minCollapsedHeight = 56.dp
private val _horizontalPadding = 16.dp
private val _expandedTitleBottomPadding = 8.dp
private val _collapsedTitleLineHeight = 28.sp
private val _defaultCollapsedElevation = 4.dp

private const val ExpandedTitleId = "expandedTitle"
private const val CollapsedTitleId = "collapsedTitle"
private const val NavigationIconId = "navigationIcon"
private const val ActionsId = "actions"
private const val CentralContentId = "centralContent"
private const val AdditionalContentId = "additionalContent"
