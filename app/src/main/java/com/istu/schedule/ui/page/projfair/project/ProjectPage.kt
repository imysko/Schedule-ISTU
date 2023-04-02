package com.istu.schedule.ui.page.projfair.project

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.ui.components.base.AppComposable

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
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .border(
                                border = BorderStroke(0.dp, SolidColor(Color.White)),
                                shape = RoundedCornerShape(4.dp),
                            ),
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
                LazyColumn {
                    item {
                        project?.let {
                            Text(
                                text = project.title,
                                style = MaterialTheme.typography.titleLarge,
                            )
                        } ?: run {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize(),
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        },
    )
}
