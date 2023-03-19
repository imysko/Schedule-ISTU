package com.istu.schedule.ui.page.projfair.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.istu.schedule.ui.components.base.AppComposable
import com.istu.schedule.ui.components.base.SIScaffold
import com.istu.schedule.ui.theme.Shapes
import com.istu.schedule.util.NavDestinations
import com.istu.schedule.util.collectAsStateValue

@Composable
fun ListPage(
    navController: NavController,
    viewModel: ListViewModel = hiltViewModel()
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
            SIScaffold (
                content = {
                    LazyColumn(
                        state = listState
                    ) {
                        items (projectsList) { project ->
                            Card(
                                shape = Shapes.medium,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate("${NavDestinations.PROJECT_SCREEN}/${project.id}")
                                    },
                            ) {
                                Column(Modifier.padding(8.dp)) {
                                    Text(
                                        project.title,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 2
                                    )
                                }
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(128.dp))
                            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
                        }
                    }
                }
            )
        }
    )
}