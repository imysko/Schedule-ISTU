package com.istu.schedule.ui.page.projfair.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.AppComposable
import com.istu.schedule.ui.components.base.SearchBar
import com.istu.schedule.ui.icons.Filter
import com.istu.schedule.util.NavDestinations
import com.istu.schedule.util.collectAsStateValue

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProjectsListPage(
    navController: NavController,
    viewModel: ListViewModel = hiltViewModel(),
) {
    val projectsListUiState = viewModel.projectsListUiState.collectAsStateValue()

    val projectsList by viewModel.projectsList.observeAsState(initial = emptyList())

    val endOfListReached by remember {
        derivedStateOf {
            projectsListUiState.listState.canScrollForward
        }
    }

    val listState = projectsListUiState.listState

    LaunchedEffect(endOfListReached) {
        viewModel.getProjectsList()
    }

    AppComposable(
        viewModel = viewModel,
        content = {
            BackdropScaffold(
                modifier = Modifier.statusBarsPadding(),
                appBar = {
                    Text(
                        modifier = Modifier.padding(15.dp),
                        text = stringResource(id = R.string.projfair),
                        style = MaterialTheme.typography.headlineMedium,
                    )
                },
                backLayerBackgroundColor = MaterialTheme.colorScheme.primary,
                backLayerContent = {
                    SearchBar(
                        modifier = Modifier.padding(start = 15.dp, bottom = 21.dp, end = 15.dp),
                        value = "Text test",
                    )
                },
                frontLayerBackgroundColor = MaterialTheme.colorScheme.surface,
                frontLayerContent = {
                    LazyColumn(
                        state = listState,
                    ) {
                        item {
                            Row(
                                modifier = Modifier.padding(15.dp).fillMaxWidth(),
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
                                    ) {
                                    },
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
                        items(projectsList) { project ->
                            ProjectItem(
                                modifier = Modifier.fillMaxWidth(),
                                project = project,
                                onClick = { navController.navigate("${NavDestinations.PROJECT_PAGE}/${project.id}") },
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(128.dp))
                            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
                        }
                    }
                },
            )
        },
    )
}
