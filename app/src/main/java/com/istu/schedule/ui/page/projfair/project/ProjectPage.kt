package com.istu.schedule.ui.page.projfair.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.ui.components.base.AppComposable
import com.istu.schedule.ui.components.base.FeedbackIconButton
import com.istu.schedule.ui.components.base.SIScaffold

@Composable
fun ProjectPage(
    projectId: Int,
    navController: NavController,
    viewModel: ProjectViewModel = hiltViewModel()
) {
    val project by viewModel.project.observeAsState(initial = null)
    viewModel.getProjectById(projectId)

    AppComposable(
        viewModel = viewModel,
        content = {
            ProjectPage(
                navController = navController,
                project = project
            )
        }
    )
}

@Composable
fun ProjectPage(
    navController: NavController,
    project: Project?
) {
    SIScaffold(
        title = {
            Text(project?.title ?: "", maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        navigationIcon = {
            FeedbackIconButton(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back),
                onClick = { navController.popBackStack() }
            )
        }
    ) {
        project?.let {
            Text(it.description)
        } ?: run {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }
}