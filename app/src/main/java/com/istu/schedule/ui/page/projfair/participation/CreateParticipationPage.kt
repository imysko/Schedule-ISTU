package com.istu.schedule.ui.page.projfair.participation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Participation
import com.istu.schedule.ui.components.base.SIAnimatedVisibilityFadeOnly
import com.istu.schedule.ui.components.base.SIDialog
import com.istu.schedule.ui.components.base.StringResourceItem
import com.istu.schedule.ui.components.base.button.FilledButton
import com.istu.schedule.ui.components.base.button.TextButton
import com.istu.schedule.ui.components.base.button.radio.RadioButtonWithText
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape20
import com.istu.schedule.ui.theme.ShapeTop15

@Composable
fun CreateParticipationPage(
    projectId: Int,
    navController: NavController,
    viewModel: CreateParticipationViewModel = hiltViewModel()
) {
    val isLoaded by viewModel.isLoaded.observeAsState(initial = false)
    val selectedPriorityId by viewModel.selectedPriorityId.observeAsState(initial = 0)
    val project by viewModel.project.observeAsState(initial = null)
    val existsParticipations by viewModel.participationsList.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchParticipationsList()
        viewModel.fetchProject(projectId)
    }

    CreateParticipationPage(
        isLoaded = isLoaded,
        existsParticipations = existsParticipations,
        selectedPriority = selectedPriorityId,
        onBackPressed = { navController.popBackStack() },
        onCreatePressed = { viewModel.createParticipation() },
        onSelect = { viewModel.setPriorityId(it) },
        projectTitle = project?.title
    )
}

@Composable
fun CreateParticipationPage(
    isLoaded: Boolean,
    existsParticipations: List<Participation>,
    selectedPriority: Int,
    onBackPressed: () -> Unit,
    onCreatePressed: () -> Unit,
    onSelect: (Int) -> Unit,
    projectTitle: String? = null
) {
    var confirmDialogVisible by remember { mutableStateOf(false) }

    val prioritiesList = mutableListOf(
        StringResourceItem(1, R.string.highest_priority),
        StringResourceItem(2, R.string.medium_priority),
        StringResourceItem(3, R.string.low_priority)
    )
    existsParticipations.forEach { participation ->
        val item = prioritiesList.firstOrNull {
            it.id == participation.priority
        }
        if (item != null) {
            prioritiesList.remove(item)
        }
    }

    SIDialog(
        modifier = Modifier.clip(Shape20),
        visible = confirmDialogVisible,
        backgroundColor = AppTheme.colorScheme.backgroundSecondary,
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.participation_submitted),
                style = AppTheme.typography.title.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = AppTheme.colorScheme.textPrimary,
                textAlign = TextAlign.Center
            )
        },
        onDismissRequest = {
            confirmDialogVisible = false
            onBackPressed()
        },
        confirmButton = {
            TextButton(
                text = stringResource(R.string.confirm),
                contentColor = AppTheme.colorScheme.primary,
                onClick = { onBackPressed() }
            )
        }
    )

    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = {
            TopBar(
                title = stringResource(R.string.send_participation_page_title),
                isShowBackButton = true,
                onBackPressed = onBackPressed
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .clip(ShapeTop15)
                .background(AppTheme.colorScheme.backgroundSecondary),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text(
                    text = projectTitle ?: stringResource(R.string.project),
                    style = AppTheme.typography.title
                )
            }
            item {
                SIAnimatedVisibilityFadeOnly(isLoaded) {
                    Column {
                        Text(
                            modifier = Modifier.padding(bottom = 10.dp),
                            text = stringResource(R.string.project_priority),
                            style = AppTheme.typography.subtitle.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            prioritiesList.map { priority ->
                                RadioButtonWithText(
                                    text = stringResource(id = priority.stringId),
                                    selected = selectedPriority == priority.id,
                                    onSelect = { onSelect(priority.id) }
                                )
                            }
                        }
                    }
                }
            }
            item {
                SIAnimatedVisibilityFadeOnly(
                    isLoaded && prioritiesList.any {
                        it.id == selectedPriority
                    }
                ) {
                    FilledButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp),
                        text = stringResource(R.string.send_participation),
                        onClick = {
                            onCreatePressed()
                            confirmDialogVisible = true
                        }
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(64.dp))
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
fun PreviewSendParticipationPage() {
    AppTheme {
        CreateParticipationPage(
            isLoaded = true,
            existsParticipations = listOf(),
            selectedPriority = 1,
            onBackPressed = {},
            onCreatePressed = {},
            onSelect = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSendParticipationPageLoading() {
    AppTheme {
        CreateParticipationPage(
            isLoaded = false,
            existsParticipations = listOf(),
            selectedPriority = 1,
            onBackPressed = {},
            onCreatePressed = {},
            onSelect = {}
        )
    }
}
