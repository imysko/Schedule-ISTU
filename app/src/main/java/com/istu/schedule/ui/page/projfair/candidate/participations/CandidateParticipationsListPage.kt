package com.istu.schedule.ui.page.projfair.candidate.participations

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.ui.components.base.SIScaffold
import com.istu.schedule.ui.theme.Shapes
import com.istu.schedule.util.NavDestinations

@Composable
fun CandidateParticipationsListPage(
    navController: NavController,
    viewModel: CandidateParticipationsListViewModel = hiltViewModel()
) {
    val participationsList by viewModel.participationsList.observeAsState(initial = emptyList())

    SIScaffold(
        content = {
            LazyColumn {
                items(participationsList) { participation ->
                    Card(
                        shape = Shapes.medium,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    "${NavDestinations.PROJECT_PAGE}/${participation.id}"
                                )
                            }
                    ) {
                        Column(Modifier.padding(8.dp)) {
                            Text(
                                participation.toString(),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 2
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
    )
}
