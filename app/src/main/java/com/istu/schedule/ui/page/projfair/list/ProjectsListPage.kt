package com.istu.schedule.ui.page.projfair.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.ui.components.base.SIExtensibleVisibility
import com.istu.schedule.ui.components.base.SearchBar
import com.istu.schedule.ui.components.projfair.ProjectItem
import com.istu.schedule.ui.components.projfair.ProjectItemPlaceHolder
import com.istu.schedule.ui.icons.Filter
import com.istu.schedule.ui.icons.Search
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.util.NavDestinations
import com.istu.schedule.util.collectAsStateValue
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun ProjectsListPage(
    navController: NavController,
    viewModel: ListViewModel = hiltViewModel()
) {
    val isLoading by viewModel.loading.observeAsState(initial = false)
    val projectsList by viewModel.projectsList.observeAsState(initial = emptyList())
    val projectsListUiState = viewModel.projectsListUiState.collectAsStateValue()
    val canCreateParticipation = viewModel.canCreateParticipation

    LaunchedEffect(projectsListUiState) {
        if (viewModel.projfairFiltersState.value.isChanged) {
            viewModel.clearList()
            viewModel.getProjectsList()
        }
    }

    ProjectsListPage(
        projectsListUiState = projectsListUiState,
        isLoading = isLoading,
        projectsList = projectsList,
        canCreateParticipation = canCreateParticipation,
        onSearchTextEdit = {
            viewModel.inputSearchContent(it)
        },
        onSearchButtonClick = {
            viewModel.changeSearchBarVisibility()
        },
        onSearchConfirmClick = {
            viewModel.clearList()
            viewModel.getProjectsList()
        },
        onProjectClick = {
            navController.navigate("${NavDestinations.PROJECT}/$it/$canCreateParticipation")
        },
        onCreateParticipationClick = {
            navController.navigate("${NavDestinations.CREATE_PARTICIPATION}/$it")
        },
        onFilterClick = {
            navController.navigate(NavDestinations.FILTERS)
        },
        onLoadMore = {
            viewModel.getProjectsList()
        }
    )
}

@Composable
fun ProjectsListPage(
    projectsListUiState: ProjectsListUiState,
    isLoading: Boolean,
    canCreateParticipation: Boolean,
    projectsList: List<Project>,
    onSearchTextEdit: (String) -> Unit,
    onSearchButtonClick: () -> Unit,
    onSearchConfirmClick: () -> Unit,
    onProjectClick: (Int) -> Unit,
    onCreateParticipationClick: (Int) -> Unit,
    onFilterClick: () -> Unit,
    onLoadMore: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val listState = projectsListUiState.listState

    Scaffold(
        containerColor = AppTheme.colorScheme.primary,
        topBar = {
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(15.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.projfair),
                        color = AppTheme.colorScheme.textSecondary,
                        style = AppTheme.typography.pageTitle
                    )
                    Column(
                        modifier = Modifier
                            .size(32.dp)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) { onSearchButtonClick() },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Search,
                            contentDescription = "search icon",
                            tint = AppTheme.colorScheme.textSecondary
                        )
                    }
                }
                SIExtensibleVisibility(visible = projectsListUiState.isSearchVisible) {
                    SearchBar(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .height(42.dp),
                        value = projectsListUiState.titleSearchText,
                        focusRequester = focusRequester,
                        placeholder = stringResource(R.string.projects_search_tint),
                        onValueChange = { onSearchTextEdit(it) },
                        onDone = { onSearchConfirmClick() }
                    )
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .clip(ShapeTop15)
                .background(AppTheme.colorScheme.background),
            state = listState
        ) {
            item {
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.all_projects),
                        style = AppTheme.typography.title
                    )
                    Column(
                        modifier = Modifier.clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) { onFilterClick() },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Filter,
                            contentDescription = "filter icon",
                            tint = AppTheme.colorScheme.textPrimary
                        )
                        Text(
                            text = stringResource(R.string.filters),
                            style = AppTheme.typography.labelMedium,
                            color = AppTheme.colorScheme.textPrimary
                        )
                    }
                }
            }
            if (projectsList.isNotEmpty()) {
                items(projectsList) { project ->
                    ProjectItem(
                        modifier = Modifier.fillMaxWidth(),
                        project = project,
                        canCreateParticipation = canCreateParticipation,
                        onClick = { onProjectClick(project.id) },
                        onCreateParticipationClick = { onCreateParticipationClick(project.id) }
                    )
                }
            }
            if (isLoading) {
                item {
                    ProjectItemPlaceHolder()
                }
            }
            item {
                Spacer(modifier = Modifier.height(128.dp))
                Spacer(
                    modifier = Modifier.windowInsetsBottomHeight(
                        WindowInsets.navigationBars
                    )
                )
            }
        }
        InfiniteListHandler(listState = listState) {
            onLoadMore()
        }
    }
}

@Composable
fun InfiniteListHandler(
    listState: LazyListState,
    buffer: Int = 2,
    onLoadMore: () -> Unit
) {
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1

            lastVisibleItemIndex > (totalItemsNumber - buffer)
        }
    }

    LaunchedEffect(loadMore) {
        snapshotFlow { loadMore.value }
            .distinctUntilChanged()
            .collect {
                onLoadMore()
            }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProjectsListPageLoading() {
    AppTheme {
        ProjectsListPage(
            projectsListUiState = ProjectsListUiState(),
            isLoading = true,
            projectsList = listOf(),
            canCreateParticipation = true,
            onSearchTextEdit = { },
            onSearchButtonClick = { },
            onSearchConfirmClick = { },
            onProjectClick = { },
            onCreateParticipationClick = { },
            onFilterClick = { },
            onLoadMore = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProjectsListPageWithSearch() {
    AppTheme {
        ProjectsListPage(
            projectsListUiState = ProjectsListUiState(
                isSearchVisible = true
            ),
            isLoading = false,
            projectsList = listOf(),
            canCreateParticipation = true,
            onSearchTextEdit = { },
            onSearchButtonClick = { },
            onSearchConfirmClick = { },
            onProjectClick = { },
            onCreateParticipationClick = { },
            onFilterClick = { },
            onLoadMore = { }
        )
    }
}
