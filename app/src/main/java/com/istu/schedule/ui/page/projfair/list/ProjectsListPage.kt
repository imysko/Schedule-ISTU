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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.SIExtensibleVisibility
import com.istu.schedule.ui.components.base.SearchBar
import com.istu.schedule.ui.icons.Filter
import com.istu.schedule.ui.icons.Search
import com.istu.schedule.ui.theme.ScheduleISTUTheme
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.util.NavDestinations
import com.istu.schedule.util.collectAsStateValue

@Composable
fun ProjectsListPage(
    navController: NavController,
    viewModel: ListViewModel = hiltViewModel()
) {
    val projectsListUiState = viewModel.projectsListUiState.collectAsStateValue()

    val projectsList by viewModel.projectsList.observeAsState(initial = emptyList())

    val listState = projectsListUiState.listState

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
        if (viewModel.projfairFiltersState.value.isChanged) {
            viewModel.clearList()
            viewModel.getProjectsList()
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            Column(modifier = Modifier.statusBarsPadding().padding(15.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.projfair),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Column(
                        modifier = Modifier
                            .size(32.dp)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) { viewModel.changeSearchBarVisibility() },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Search,
                            contentDescription = "search icon"
                        )
                    }
                }
                SIExtensibleVisibility(visible = projectsListUiState.isSearchVisible) {
                    SearchBar(
                        modifier = Modifier.padding(top = 15.dp).height(42.dp),
                        value = projectsListUiState.titleSearchText,
                        focusRequester = focusRequester,
                        placeholder = stringResource(R.string.projects_search_tint),
                        onValueChange = {
                            viewModel.inputSearchContent(it)
                        },
                        onDone = {
                            viewModel.clearList()
                            viewModel.getProjectsList()
                        }
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
                .background(MaterialTheme.colorScheme.background),
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
                        style = MaterialTheme.typography.titleLarge
                    )
                    Column(
                        modifier = Modifier.clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) { navController.navigate(NavDestinations.FILTERS_PAGE) },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Filter,
                            contentDescription = "filter icon",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = stringResource(R.string.filters),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 10.sp
                            )
                        )
                    }
                }
            }
            if (projectsList.isNotEmpty()) {
                items(projectsList) { project ->
                    ProjectItem(
                        modifier = Modifier.fillMaxWidth(),
                        project = project,
                        onClick = {
                            navController.navigate(
                                "${NavDestinations.PROJECT_PAGE}/${project.id}"
                            )
                        }
                    )
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
}

@Preview(showBackground = true)
@Composable
fun PreviewProjectsListPage() {
    ScheduleISTUTheme {
        ProjectsListPage(
            navController = rememberNavController()
        )
    }
}
