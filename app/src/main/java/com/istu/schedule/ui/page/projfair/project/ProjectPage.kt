package com.istu.schedule.ui.page.projfair.project

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.ui.components.base.AppComposable
import com.istu.schedule.ui.components.base.FilledButton
import com.istu.schedule.ui.components.base.TwoColumnText
import com.istu.schedule.ui.icons.People
import com.istu.schedule.ui.theme.HalfGray
import com.istu.schedule.util.toProjectDifficulty
import java.text.DateFormat

@Composable
fun ProjectPage(
    projectId: Int,
    navController: NavController,
    viewModel: ProjectViewModel = hiltViewModel(),
) {
    val project by viewModel.project.observeAsState(initial = null)
    viewModel.getProjectById(projectId)

    AppComposable(
        viewModel = viewModel,
        content = {
            ProjectPage(
                navController = navController,
                project = project,
            )
        },
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProjectPage(
    navController: NavController,
    project: Project?,
) {
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
            Row {
            }
        },
        frontLayerBackgroundColor = MaterialTheme.colorScheme.surface,
        frontLayerContent = {
            Column(
                modifier = Modifier.padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 23.dp,
                    bottom = 50.dp,
                ),
            ) {
                Box(
                    modifier = Modifier.clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                    ) {
                        navController.popBackStack()
                    },
                ) {
                    Row(
                        modifier = Modifier.padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector = Icons.Rounded.ArrowBackIosNew,
                            contentDescription = stringResource(R.string.back),
                            tint = MaterialTheme.colorScheme.secondary,
                        )
                        Text(
                            modifier = Modifier.padding(start = 9.dp),
                            text = stringResource(R.string.back_to_list),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.SemiBold,
                            ),
                        )
                    }
                }
                project?.let { project ->
                    Text(
                        text = project.title,
                        style = MaterialTheme.typography.titleLarge,
                    )
                    LazyColumn(
                        modifier = Modifier.padding(top = 23.dp),
                    ) {
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(bottom = 22.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Column {
                                    Text(
                                        modifier = Modifier.padding(bottom = 10.dp),
                                        text = stringResource(R.string.maximum_participants),
                                        style = MaterialTheme.typography.bodyMedium,
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Icon(
                                            modifier = Modifier.size(28.dp),
                                            imageVector = Icons.People,
                                            contentDescription = "People Icon",
                                            tint = MaterialTheme.colorScheme.primary,
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = project.places.toString(),
                                            style = MaterialTheme.typography.bodyLarge.copy(
                                                color = MaterialTheme.colorScheme.primary,
                                                fontWeight = FontWeight.SemiBold,
                                            ),
                                        )
                                    }
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(
                                        modifier = Modifier.padding(bottom = 10.dp),
                                        text = stringResource(R.string.project_status),
                                        style = MaterialTheme.typography.bodyMedium,
                                    )
                                    Box(
                                        modifier = Modifier
                                            .border(
                                                BorderStroke(
                                                    width = 1.45.dp,
                                                    MaterialTheme.colorScheme.primary,
                                                ),
                                                RoundedCornerShape(72.dp),
                                            )
                                            .padding(24.dp, 7.dp),
                                    ) {
                                        Text(
                                            text = project.state.state.uppercase(),
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = MaterialTheme.colorScheme.primary,
                                            ),
                                        )
                                    }
                                }
                            }
                        }
                        item {
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(2.dp),
                                color = HalfGray,
                            )
                        }
                        item {
                            TwoColumnText(
                                modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
                                key = stringResource(R.string.project_manager),
                                value = project.supervisorsNames,
                            )
                        }
                        project.customer.isNotBlank().let { isNotBlank ->
                            if (isNotBlank) {
                                item {
                                    TwoColumnText(
                                        modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
                                        key = stringResource(R.string.customer),
                                        value = project.customer,
                                    )
                                }
                            }
                        }
                        item {
                            TwoColumnText(
                                modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
                                key = stringResource(R.string.timeline),
                                value = "${DateFormat.getDateInstance(DateFormat.LONG).format(project.dateStart)} - ${DateFormat.getDateInstance(DateFormat.LONG).format(project.dateEnd)}",
                            )
                        }
                        item {
                            TwoColumnText(
                                modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
                                key = stringResource(R.string.difficulty),
                                value = project.difficulty.toProjectDifficulty(),
                            )
                        }
                        item {
                            TwoColumnText(
                                modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
                                key = stringResource(R.string.type_of_project),
                                value = project.type.type,
                            )
                        }
                        item {
                            TwoColumnText(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 22.dp),
                                key = stringResource(R.string.project_goal),
                                value = project.goal,
                            )
                        }
                        item {
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(2.dp),
                                color = HalfGray,
                            )
                        }
                        item {
                            TwoColumnText(
                                modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
                                key = stringResource(R.string.expected_result),
                                value = project.productResult,
                            )
                        }
                        item {
                            TwoColumnText(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 22.dp),
                                key = stringResource(R.string.requirements_for_participants),
                                value = project.studyResult,
                            )
                        }
                        item {
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(2.dp),
                                color = HalfGray,
                            )
                        }
                        item {
                            Column(modifier = Modifier.padding(vertical = 22.dp)) {
                                Text(
                                    style = MaterialTheme.typography.bodySmall,
                                    text = stringResource(R.string.project_idea),
                                )
                                Spacer(Modifier.height(11.dp))
                                Text(
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = FontWeight.Bold,
                                    ),
                                    text = project.description,
                                )
                            }
                            Text(
                                style = MaterialTheme.typography.bodySmall,
                                text = stringResource(R.string.required_skills),
                            )
                        }
                        item {
                            FilledButton(
                                modifier = Modifier.padding(top = 22.dp).fillMaxWidth().height(42.dp),
                                text = stringResource(R.string.send_application),
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(64.dp))
                            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
                        }
                    }
                } ?: run {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        },
    )
}
