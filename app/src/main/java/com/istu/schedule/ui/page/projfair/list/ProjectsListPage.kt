package com.istu.schedule.ui.page.projfair.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.domain.model.projfair.SampleProjectProvider
import com.istu.schedule.ui.components.base.SIExtensibleVisibility
import com.istu.schedule.ui.components.base.SIExtensibleVisibilityFadeOnly
import com.istu.schedule.ui.components.base.SearchBar
import com.istu.schedule.ui.components.projfair.ProjectItem
import com.istu.schedule.ui.components.projfair.ProjectItemPlaceHolder
import com.istu.schedule.ui.icons.Filter
import com.istu.schedule.ui.icons.Logo152
import com.istu.schedule.ui.icons.Search
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.util.NavDestinations
import com.istu.schedule.util.OnBottomReached
import com.istu.schedule.util.collectAsStateValue

@Composable
fun ProjectsListPage(
    navController: NavController,
    viewModel: ListViewModel = hiltViewModel()
) {
    val isLoading by viewModel.loading.observeAsState(initial = true)
    val isSearchCompleted by viewModel.isSearchCompleted.observeAsState(initial = false)
    val projectsList by viewModel.projectsList.observeAsState(initial = emptyList())
    val projectsListUiState = viewModel.projectsListUiState.collectAsStateValue()
    val projfairFiltersState = viewModel.projfairFiltersState.collectAsStateValue()
    val canCreateParticipation = viewModel.canCreateParticipation

    LaunchedEffect(projfairFiltersState) {
        if (viewModel.projfairFiltersState.value.isChanged) {
            viewModel.clearList()
            viewModel.getProjectsList()
        }
    }

    ProjectsListPage(
        projectsListUiState = projectsListUiState,
        isLoading = isLoading,
        isSearchCompleted = isSearchCompleted,
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
    isSearchCompleted: Boolean,
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

    listState.OnBottomReached(buffer = 2) {
        onLoadMore()
    }

    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundPrimary,
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
                    IconButton(
                        modifier = Modifier.size(32.dp),
                        onClick = { onSearchButtonClick() },
                        content = {
                            Icon(
                                imageVector = Icons.Search,
                                contentDescription = stringResource(id = R.string.search),
                                tint = AppTheme.colorScheme.textSecondary
                            )
                        }
                    )
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
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = it.calculateTopPadding())
                .clip(ShapeTop15)
                .background(AppTheme.colorScheme.backgroundSecondary)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                state = listState,
                contentPadding = PaddingValues(
                    top = 15.dp,
                    start = 15.dp,
                    end = 15.dp
                )
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
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
                    item {
                        SIExtensibleVisibilityFadeOnly(visible = isLoading) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier.size(48.dp),
                                    imageVector = Icons.Logo152,
                                    contentDescription = "Loading Icon",
                                    tint = Color.Unspecified
                                )
                            }
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
            }
            SIExtensibleVisibilityFadeOnly(visible = projectsList.isEmpty()) {
                Box {
                    SIExtensibleVisibilityFadeOnly(visible = !isLoading && isSearchCompleted) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp),
                            verticalArrangement = Arrangement.spacedBy(
                                space = 15.dp,
                                alignment = Alignment.CenterVertically
                            )
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(R.string.projects_not_found),
                                style = AppTheme.typography.title.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = AppTheme.colorScheme.secondary,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 15.dp),
                                text = stringResource(R.string.projects_not_found_desc),
                                style = AppTheme.typography.subtitle,
                                color = AppTheme.colorScheme.secondary,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    SIExtensibleVisibilityFadeOnly(visible = isLoading) {
                        Column(
                            modifier = Modifier.padding(
                                top = 15.dp,
                                start = 15.dp,
                                end = 15.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            ProjectItemPlaceHolder()
                            ProjectItemPlaceHolder()
                        }
                    }
                }
            }
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
            isSearchCompleted = false,
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
fun PreviewProjectsListPageEmpty() {
    AppTheme {
        ProjectsListPage(
            projectsListUiState = ProjectsListUiState(),
            isLoading = false,
            isSearchCompleted = true,
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
fun PreviewProjectsListPageWithSearch(
    @PreviewParameter(SampleProjectProvider::class) project: Project
) {
    AppTheme {
        ProjectsListPage(
            projectsListUiState = ProjectsListUiState(
                isSearchVisible = true
            ),
            isLoading = false,
            isSearchCompleted = true,
            projectsList = listOf(project),
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
fun PreviewProjectsListPageWithLoading(
    @PreviewParameter(SampleProjectProvider::class) project: Project
) {
    AppTheme {
        ProjectsListPage(
            projectsListUiState = ProjectsListUiState(
                isSearchVisible = false
            ),
            isLoading = true,
            isSearchCompleted = false,
            projectsList = listOf(project),
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
