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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.ui.components.base.AppComposable
import com.istu.schedule.ui.components.base.CheckboxGroup
import com.istu.schedule.ui.components.base.CheckboxItem
import com.istu.schedule.ui.components.base.FilledButton
import com.istu.schedule.ui.components.base.SIDropdownMenu
import com.istu.schedule.ui.components.base.SearchBar
import com.istu.schedule.ui.components.base.TextButton
import com.istu.schedule.ui.icons.Filter
import com.istu.schedule.ui.icons.X
import com.istu.schedule.ui.theme.ScheduleISTUTheme
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

    val listState = projectsListUiState.listState

    val endOfListReached by remember {
        derivedStateOf {
            listState.canScrollForward
        }
    }

    val focusRequester = remember { FocusRequester() }

    val statusesList = listOf(
            CheckboxItem(1, stringResource(R.string.recruitment_is_open)),
            CheckboxItem(2, stringResource(R.string.processing_of_participants)),
            CheckboxItem(4, stringResource(R.string.completion_of_participants)),
            CheckboxItem(5, stringResource(R.string.in_archive)),
    )

    val difficultiesList = listOf(
            CheckboxItem(1, stringResource(R.string.easy)),
            CheckboxItem(2, stringResource(R.string.medium)),
            CheckboxItem(3, stringResource(R.string.difficult)),
    )

    LaunchedEffect(endOfListReached) {
        if (!listState.canScrollForward) {
            viewModel.getProjectsList()
        }
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
                        value = projectsListUiState.titleSearchText,
                        focusRequester = focusRequester,
                        placeholder = stringResource(R.string.projects_search_tint),
                        onValueChange = {
                            viewModel.inputSearchContent(it)
                        },
                        onDone = {
                            viewModel.clearList()
                            viewModel.getProjectsList()
                        },
                    )
                },
                frontLayerBackgroundColor = MaterialTheme.colorScheme.surface,
                frontLayerContent = {
                    if (projectsListUiState.isFiltersPageOpen) {
                        FiltersPage(
                            statusesList = statusesList,
                            difficultiesList = difficultiesList,
                            onCloseClick = { viewModel.setFiltersPageStatus(false) },
                            onApplyClick = {},
                            onResetClick = {},
                        )
                    } else {
                        ProjectsList(
                            listState = listState,
                            projectsList = projectsList,
                            navController = navController,
                            onFilterClick = { viewModel.setFiltersPageStatus(true) },
                        )
                    }
                },
            )
        },
    )
}

@Composable
fun ProjectsList(
    listState: LazyListState,
    projectsList: List<Project>,
    navController: NavController,
    onFilterClick: () -> Unit,
) {
    LazyColumn(
        state = listState,
    ) {
        item {
            Row(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
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
                        onFilterClick()
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
}

@Composable
fun FiltersPage(
    modifier: Modifier = Modifier,
    statusesList: List<CheckboxItem>,
    difficultiesList: List<CheckboxItem>,
    onCloseClick: () -> Unit,
    onApplyClick: () -> Unit,
    onResetClick: () -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(15.dp),
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.filters),
                    style = MaterialTheme.typography.titleLarge,
                )
                Column(
                    modifier = Modifier
                        .padding(7.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                        ) {
                            onCloseClick()
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        imageVector = Icons.X,
                        contentDescription = "cross icon",
                        tint = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        }
        item {
            Text(
                modifier = Modifier.padding(top = 14.dp, bottom = 4.dp, start = 5.dp),
                text = stringResource(R.string.project_status),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            )
        }
        item {
            CheckboxGroup(
                items = statusesList,
            )
        }
        item {
            Divider(Modifier.padding(vertical = 22.dp))
        }
        item {
            Text(
                modifier = Modifier.padding(bottom = 14.dp, start = 5.dp),
                text = stringResource(R.string.project_tags),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            )
        }
        item {
            SIDropdownMenu(
                listItems = listOf(),
                selectedItems = listOf(),
                placeholder = stringResource(R.string.select_a_specialty),
            )
        }
        item {
            SIDropdownMenu(
                modifier = Modifier.padding(top = 8.dp),
                listItems = listOf(),
                selectedItems = listOf(),
                placeholder = stringResource(R.string.select_a_skill),
            )
        }
        item {
            Divider(Modifier.padding(vertical = 22.dp))
        }
        item {
            Text(
                modifier = Modifier.padding(bottom = 4.dp, start = 5.dp),
                text = stringResource(R.string.level_of_difficulty),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            )
        }
        item {
            CheckboxGroup(
                items = difficultiesList,
            )
        }
        item {
            FilledButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp)
                    .height(42.dp),
                text = stringResource(R.string.find),
                onClick = { onApplyClick() },
            )
        }
        item {
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                text = stringResource(R.string.reset_filter),
                onClick = { onResetClick() },
            )
        }
        item {
            Spacer(modifier = Modifier.height(128.dp))
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFiltersPage() {
    ScheduleISTUTheme {
        FiltersPage(
            statusesList = mutableListOf(
                CheckboxItem(1, stringResource(R.string.recruitment_is_open)),
                CheckboxItem(2, stringResource(R.string.processing_of_participants)),
                CheckboxItem(4, stringResource(R.string.completion_of_participants)),
                CheckboxItem(5, stringResource(R.string.in_archive)),
            ),
            difficultiesList = mutableListOf(
                CheckboxItem(1, stringResource(R.string.easy)),
                CheckboxItem(2, stringResource(R.string.medium)),
                CheckboxItem(3, stringResource(R.string.difficult)),
            ),
            onCloseClick = { },
            onApplyClick = { },
            onResetClick = { },
        )
    }
}
