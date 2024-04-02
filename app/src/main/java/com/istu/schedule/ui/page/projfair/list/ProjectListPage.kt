package com.istu.schedule.ui.page.projfair.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.istu.schedule.data.api.entities.projfair.enums.ListStatus
import com.istu.schedule.ui.components.base.NoInternetPanel
import com.istu.schedule.ui.components.base.SIAnimatedVisibility
import com.istu.schedule.ui.components.base.SIAnimatedVisibilityFadeOnly
import com.istu.schedule.ui.components.base.SearchBar
import com.istu.schedule.ui.components.projfair.ProjectItem
import com.istu.schedule.ui.components.projfair.ProjectItemPlaceHolder
import com.istu.schedule.ui.icons.Filter
import com.istu.schedule.ui.icons.Logo152
import com.istu.schedule.ui.icons.Search
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape5
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.ui.util.NavDestinations
import com.istu.schedule.ui.util.previewParameterProviders.SampleProjectPreviewParameterProvider
import com.istu.schedule.util.OnBottomReached
import com.istu.schedule.util.collectAsStateValue
import me.progneo.projfair.domain.model.Project

@Composable
fun ProjectsListPage(
    navController: NavController,
    viewModel: ProjectsListViewModel = hiltViewModel()
) {
    val pageStatus by viewModel.pageStatus.observeAsState(initial = ListStatus.Complete)
    val projectsList by viewModel.projectList.observeAsState(initial = emptyList())
    val canCreateParticipation = viewModel.canCreateParticipation.collectAsStateValue()
    val projectsListUiState = viewModel.projectListUiState.collectAsStateValue()
    val projfairFiltersState = viewModel.projfairFiltersState.collectAsStateValue()

    LaunchedEffect(projfairFiltersState) {
        if (projfairFiltersState.isChanged) {
            viewModel.clearList()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchParticipationList()
        viewModel.fetchProjectList()
    }

    ProjectsListPage(
        projectListUiState = projectsListUiState,
        listStatus = pageStatus,
        projectList = projectsList,
        onSearchTextEdit = {
            viewModel.inputSearchContent(it)
        },
        onSearchPress = {
            viewModel.clearList()
            viewModel.fetchProjectList()
        },
        onProjectPress = {
            navController.navigate("${NavDestinations.PROJECT}/$it/$canCreateParticipation")
        },
        onFilterPress = {
            navController.navigate(NavDestinations.FILTERS)
        },
        onLoadMore = {
            viewModel.fetchProjectList()
        }
    )
}

@Composable
fun ProjectsListPage(
    projectListUiState: ProjectListUiState,
    projectList: List<Project>,
    listStatus: ListStatus,
    onSearchTextEdit: (String) -> Unit,
    onSearchPress: () -> Unit,
    onProjectPress: (Int) -> Unit,
    onFilterPress: () -> Unit,
    onLoadMore: () -> Unit
) {
    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = {
            TopBar(
                searchText = projectListUiState.searchText,
                onSearchPress = onSearchPress,
                onSearchTextEdit = onSearchTextEdit
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(it)
                .clip(ShapeTop15)
                .background(AppTheme.colorScheme.backgroundSecondary)
        ) {
            FilterRow(onFilterPress)
            Box {
                SIAnimatedVisibilityFadeOnly(listStatus == ListStatus.NoNetwork) {
                    NoInternetPanel()
                }

                SIAnimatedVisibilityFadeOnly(listStatus == ListStatus.FirstLoading) {
                    ListPlaceHolders()
                }

                SIAnimatedVisibilityFadeOnly(
                    projectList.isEmpty() && listStatus == ListStatus.Complete
                ) {
                    NotFoundPanel()
                }

                SIAnimatedVisibilityFadeOnly(
                    listStatus == ListStatus.Complete || listStatus == ListStatus.Loading
                ) {
                    ProjectList(
                        projectList = projectList,
                        listStatus = listStatus,
                        onProjectPress = onProjectPress,
                        onBottomReached = onLoadMore
                    )
                }
            }
        }
    }
}

@Composable
private fun FilterRow(onFilterPress: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.list_of_projects),
            style = AppTheme.typography.title
        )
        Column(
            modifier = Modifier
                .clip(Shape5)
                .clickable { onFilterPress() }
                .padding(3.dp),
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

@Composable
private fun ProjectList(
    projectList: List<Project>,
    listStatus: ListStatus,
    onProjectPress: (Int) -> Unit,
    onBottomReached: () -> Unit
) {
    val listState = rememberLazyListState()
    listState.OnBottomReached(buffer = 2) {
        onBottomReached()
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        state = listState,
        contentPadding = PaddingValues(start = 15.dp, end = 15.dp, bottom = 15.dp)
    ) {
        items(projectList) { project ->
            ProjectItem(
                modifier = Modifier.fillMaxWidth(),
                project = project
            ) { onProjectPress(project.id) }
        }

        item {
            SIAnimatedVisibilityFadeOnly(
                visible = listStatus == ListStatus.Loading
            ) {
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
            Spacer(modifier = Modifier.height(64.dp))
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@Composable
private fun ListPlaceHolders() {
    Column(
        modifier = Modifier.padding(horizontal = 15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        ProjectItemPlaceHolder()
        ProjectItemPlaceHolder()
    }
}

@Composable
private fun NotFoundPanel() {
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

@Composable
private fun TopBar(
    onSearchTextEdit: (String) -> Unit,
    onSearchPress: () -> Unit,
    searchText: String
) {
    var isSearchVisible by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

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
                onClick = { isSearchVisible = !isSearchVisible },
                content = {
                    Icon(
                        imageVector = Icons.Search,
                        contentDescription = stringResource(id = R.string.search),
                        tint = AppTheme.colorScheme.textSecondary
                    )
                }
            )
        }
        SIAnimatedVisibility(visible = isSearchVisible) {
            SearchBar(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .height(42.dp),
                value = searchText,
                placeholder = stringResource(R.string.projects_search_tint),
                focusRequester = focusRequester,
                onValueChange = { onSearchTextEdit(it) }
            ) { onSearchPress() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProjectsListPageLoading() {
    AppTheme {
        ProjectsListPage(
            projectListUiState = ProjectListUiState(),
            listStatus = ListStatus.FirstLoading,
            projectList = listOf(),
            onSearchTextEdit = {},
            onSearchPress = {},
            onProjectPress = { },
            onFilterPress = { }
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProjectsListPageEmpty() {
    AppTheme {
        ProjectsListPage(
            projectListUiState = ProjectListUiState(),
            listStatus = ListStatus.Complete,
            projectList = listOf(),
            onSearchTextEdit = {},
            onSearchPress = {},
            onProjectPress = { },
            onFilterPress = { }
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProjectListPageWithLoading(
    @PreviewParameter(SampleProjectPreviewParameterProvider::class) project: Project
) {
    AppTheme {
        ProjectsListPage(
            projectListUiState = ProjectListUiState(),
            listStatus = ListStatus.Loading,
            projectList = listOf(project),
            onSearchTextEdit = {},
            onSearchPress = {},
            onProjectPress = { },
            onFilterPress = { }
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProjectsListPageNoNetworkConnection() {
    AppTheme {
        ProjectsListPage(
            projectListUiState = ProjectListUiState(),
            listStatus = ListStatus.NoNetwork,
            projectList = listOf(),
            onSearchTextEdit = {},
            onSearchPress = {},
            onProjectPress = { },
            onFilterPress = { }
        ) { }
    }
}
