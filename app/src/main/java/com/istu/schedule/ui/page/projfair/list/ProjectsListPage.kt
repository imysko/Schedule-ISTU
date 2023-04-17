package com.istu.schedule.ui.page.projfair.list

import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.animation.core.animateDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.AppComposable
import com.istu.schedule.ui.components.toolbar.ExitUntilCollapsedState
import com.istu.schedule.ui.components.toolbar.FixedScrollFlagState
import com.istu.schedule.ui.components.toolbar.IToolbarState
import com.istu.schedule.ui.icons.Filter
import com.istu.schedule.ui.theme.ScheduleISTUTheme
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.util.NavDestinations
import com.istu.schedule.util.collectAsStateValue
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

private val _minToolbarHeight = 96.dp
private val _maxToolbarHeight = 156.dp

@Composable
private fun rememberToolbarState(toolbarHeightRange: IntRange): IToolbarState {
    return rememberSaveable(saver = ExitUntilCollapsedState.Saver) {
        ExitUntilCollapsedState(toolbarHeightRange)
    }
}

@Composable
fun ProjectsListPage(
    navController: NavController,
    viewModel: ListViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()

    val projectsListUiState = viewModel.projectsListUiState.collectAsStateValue()

    val projectsList by viewModel.projectsList.observeAsState(initial = emptyList())

    val listState = projectsListUiState.listState

    val toolbarHeightRange = with(LocalDensity.current) {
        _minToolbarHeight.roundToPx().._maxToolbarHeight.roundToPx()
    }
    val toolbarState = rememberToolbarState(toolbarHeightRange)

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource,
            ): Offset {
                toolbarState.scrollTopLimitReached =
                    listState.firstVisibleItemIndex == 0 &&
                    listState.firstVisibleItemScrollOffset == 0
                toolbarState.scrollOffset = toolbarState.scrollOffset - available.y
                return Offset(0f, toolbarState.consumed)
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                if (available.y > 0) {
                    scope.launch {
                        animateDecay(
                            initialValue = toolbarState.height + toolbarState.offset,
                            initialVelocity = available.y,
                            animationSpec = FloatExponentialDecaySpec(),
                        ) { value, _ ->
                            toolbarState.scrollTopLimitReached =
                                listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
                            toolbarState.scrollOffset =
                                toolbarState.scrollOffset - (value - (toolbarState.height + toolbarState.offset))
                            if (toolbarState.scrollOffset == 0f) scope.coroutineContext.cancelChildren()
                        }
                    }
                }

                return super.onPostFling(consumed, available)
            }
        }
    }

    val endOfListReached by remember {
        derivedStateOf {
            listState.canScrollForward
        }
    }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(endOfListReached) {
        if (!listState.canScrollForward) {
            viewModel.getProjectsList()
        }
    }

    LaunchedEffect(projectsListUiState) {
        viewModel.clearList()
        viewModel.getProjectsList()
    }

    AppComposable(
        viewModel = viewModel,
        content = {
            Box(
                modifier = Modifier
                    .nestedScroll(nestedScrollConnection)
                    .background(MaterialTheme.colorScheme.surface),
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { translationY = toolbarState.height + toolbarState.offset }
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onPress = { scope.coroutineContext.cancelChildren() },
                            )
                        }
                        .clip(ShapeTop15)
                        .background(MaterialTheme.colorScheme.background),
                    state = listState,
                    contentPadding = PaddingValues(bottom = if (toolbarState is FixedScrollFlagState) _minToolbarHeight else 0.dp),
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .padding(15.dp)
                                .fillMaxWidth()
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = { scope.coroutineContext.cancelChildren() },
                                    )
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = stringResource(R.string.all_projects),
                                style = MaterialTheme.typography.titleLarge,
                            )
                            Column(
                                modifier = Modifier.clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null,
                                ) { navController.navigate(NavDestinations.FILTERS_PAGE) },
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Icon(
                                    imageVector = Icons.Filter,
                                    contentDescription = "filter icon",
                                )
                                Text(
                                    text = stringResource(R.string.filters),
                                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                                )
                            }
                        }
                    }
                    if (projectsList.isNotEmpty()) {
                        items(projectsList) { project ->
                            ProjectItem(
                                modifier = Modifier.fillMaxWidth(),
                                project = project,
                                onClick = { navController.navigate("${NavDestinations.PROJECT_PAGE}/${project.id}") },
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(128.dp))
                        Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
                    }
                }
                SearchToolbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(with(LocalDensity.current) { toolbarState.height.toDp() })
                        .graphicsLayer { translationY = toolbarState.offset },
                    progress = toolbarState.progress,
                    searchValue = projectsListUiState.titleSearchText,
                    focusRequester = focusRequester,
                    onValueChange = {
                        viewModel.inputSearchContent(it)
                    },
                    onDone = {
                        viewModel.clearList()
                        viewModel.getProjectsList()
                    },
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewProjectsListPage() {
    ScheduleISTUTheme {
        ProjectsListPage(
            navController = rememberNavController(),
        )
    }
}
