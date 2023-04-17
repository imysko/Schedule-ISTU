package com.istu.schedule.ui.page.projfair.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.SearchBar
import com.istu.schedule.ui.theme.ScheduleISTUTheme
import kotlin.math.roundToInt

private val _contentPadding = 15.dp
private val _elevation = 4.dp
private val _buttonSize = 24.dp

private val _expandedPadding = 1.dp
private val _collapsedPadding = 3.dp

@Composable
fun SearchToolbar(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = FocusRequester(),
    progress: Float,
    searchValue: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        elevation = _elevation,
        modifier = modifier,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = _contentPadding)
                    .fillMaxSize(),
            ) {
                SearchToolbarLayout(progress = progress) {
                    Text(
                        modifier = Modifier.padding(vertical = 15.dp),
                        text = stringResource(id = R.string.projfair),
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    SearchBar(
                        modifier = Modifier
                            .height(42.dp)
                            .graphicsLayer {
                                alpha = progress
                            },
                        value = searchValue,
                        focusRequester = focusRequester,
                        placeholder = stringResource(R.string.projects_search_tint),
                        onValueChange = { onValueChange(it) },
                        onDone = { onDone() },
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchToolbarLayout(
    progress: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val placeables = measurables.map {
            it.measure(constraints)
        }
        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight,
        ) {
            val expandedHorizontalGuideline = (constraints.maxHeight * 0.4f).roundToInt()
            val collapsedHorizontalGuideline = (constraints.maxHeight * 0.5f).roundToInt()

            val title = placeables[0]
            val searchBar = placeables[1]

            title.placeRelative(
                x = 0,
                y = 0,
            )

            searchBar.placeRelative(
                x = 0,
                y = title.height,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchToolbarCollapsedPreview() {
    ScheduleISTUTheme {
        SearchToolbar(
            progress = 0f,
            searchValue = "Рейтинг студентов",
            onValueChange = {},
            onDone = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchToolbarHalfwayPreview() {
    ScheduleISTUTheme {
        SearchToolbar(
            progress = 0.5f,
            searchValue = "Рейтинг студентов",
            onValueChange = {},
            onDone = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchToolbarExpandedPreview() {
    ScheduleISTUTheme {
        SearchToolbar(
            progress = 1f,
            searchValue = "Рейтинг студентов",
            onValueChange = {},
            onDone = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
        )
    }
}
