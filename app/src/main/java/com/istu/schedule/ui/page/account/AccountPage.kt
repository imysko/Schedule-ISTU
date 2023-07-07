package com.istu.schedule.ui.page.account

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.material.placeholder
import com.istu.schedule.R
import com.istu.schedule.data.enums.ProjfairAuthStatus
import com.istu.schedule.domain.model.projfair.Candidate
import com.istu.schedule.domain.model.projfair.Participation
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.domain.model.projfair.SampleCandidateProvider
import com.istu.schedule.domain.model.projfair.SampleParticipationProvider
import com.istu.schedule.ui.components.base.CustomIndicator
import com.istu.schedule.ui.components.base.InfoBlock
import com.istu.schedule.ui.components.base.SIDialog
import com.istu.schedule.ui.components.base.SIScrollableTabRow
import com.istu.schedule.ui.components.base.SITabPosition
import com.istu.schedule.ui.components.base.button.FilledButton
import com.istu.schedule.ui.components.base.button.OutlineButton
import com.istu.schedule.ui.components.base.button.TextButton
import com.istu.schedule.ui.components.projfair.ParticipationItem
import com.istu.schedule.ui.components.projfair.ProjectItem
import com.istu.schedule.ui.icons.X
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.HalfGray
import com.istu.schedule.ui.theme.Shape20
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.util.NavDestinations
import kotlinx.coroutines.launch

@Composable
fun AccountPage(
    bottomNavController: NavController,
    navController: NavController,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val candidate by viewModel.candidate.observeAsState(initial = null)
    val authState by viewModel.authState.observeAsState(ProjfairAuthStatus.UNDEFINED)

    LaunchedEffect(Unit) {
        viewModel.collectSettingsState()
    }

    if (authState == ProjfairAuthStatus.SUCCESS) {
        AuthorizedPage(
            navController = bottomNavController,
            candidate = candidate,
            viewModel = viewModel
        )
    } else {
        LoginPage(
            onLoginPressed = { navController.navigate(NavDestinations.PROJFAIR_LOGIN) }
        )
    }
}

@Composable
fun AuthorizedPage(
    navController: NavController,
    candidate: Candidate?,
    viewModel: AccountViewModel
) {
    val participationsList by viewModel.participationsList.observeAsState(initial = emptyList())
    val projectsList by viewModel.projectsList.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.getCandidateInfo()
    }

    AuthorizedPage(
        candidate = candidate,
        participationsList = participationsList,
        projectsList = projectsList.toMutableList(),
        onProjectPressed = {
            navController.navigate(
                "${NavDestinations.PROJECT}/$it/false"
            )
        },
        onParticipationPressed = {
            navController.navigate(
                "${NavDestinations.PROJECT}/$it/${viewModel.canCreateParticipation}"
            )
        },
        onDeletePressed = {
            viewModel.deleteParticipation(it)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthorizedPage(
    candidate: Candidate?,
    participationsList: List<Participation>,
    projectsList: MutableList<Project>,
    onProjectPressed: (Int) -> Unit = {},
    onParticipationPressed: (Int) -> Unit = {},
    onDeletePressed: (Int) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val pages = listOf(
        stringResource(R.string.my_profile),
        stringResource(R.string.my_participations),
        stringResource(R.string.my_projects)
    )
    val indicator = @Composable { tabPositions: List<SITabPosition> ->
        CustomIndicator(tabPositions, pagerState)
    }

    Scaffold(
        containerColor = AppTheme.colorScheme.primary,
        topBar = {
            Column(modifier = Modifier.statusBarsPadding()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(15.dp)
                            .placeholder(
                                visible = candidate == null,
                                color = AppTheme.colorScheme.primaryContainer.copy(alpha = 0.1f),
                                highlight = PlaceholderHighlight.fade(
                                    highlightColor = AppTheme.colorScheme.primaryContainer.copy(
                                        alpha = 0.13f
                                    )
                                ),
                                shape = RoundedCornerShape(4.dp)
                            ),
                        text = candidate?.fio ?: "",
                        style = AppTheme.typography.pageTitle,
                        color = AppTheme.colorScheme.textSecondary
                    )
                    Row(
                        modifier = Modifier
                            .clickable {}
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.logout),
                            style = AppTheme.typography.title,
                            color = AppTheme.colorScheme.textSecondary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.X,
                            contentDescription = "Logout Icon",
                            tint = AppTheme.colorScheme.textSecondary
                        )
                    }
                }
                SIScrollableTabRow(
                    modifier = Modifier.padding(bottom = 10.dp),
                    selectedTabIndex = pagerState.currentPage,
                    indicator = indicator,
                    edgePadding = 15.dp
                ) {
                    pages.forEachIndexed { index, title ->
                        Column(
                            modifier = Modifier
                                .height(50.dp)
                                .padding(end = if (index != pages.size - 1) 20.dp else 0.dp)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null
                                ) {
                                    coroutineScope.launch {
                                        pagerState.scrollToPage(index)
                                    }
                                },
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                style = AppTheme.typography.title.copy(
                                    color = AppTheme.colorScheme.textSecondary
                                ),
                                text = title
                            )
                        }
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .clip(ShapeTop15)
                .background(AppTheme.colorScheme.background)
        ) {
            HorizontalPager(
                pageCount = pages.size,
                state = pagerState
            ) { page ->
                when (page) {
                    0 -> {
                        ProfilePage(candidate = candidate)
                    }

                    1 -> {
                        ParticipationsPage(
                            participationsList = participationsList,
                            isCanEdit = candidate?.canSendParticipations == 1,
                            onParticipationPressed = onParticipationPressed,
                            onDeletePressed = onDeletePressed
                        )
                    }

                    2 -> {
                        ProjectsPage(
                            projectsList = projectsList,
                            onProjectPressed = onProjectPressed
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoginPage(
    onLoginPressed: () -> Unit = {}
) {
    Scaffold(
        containerColor = AppTheme.colorScheme.primary,
        topBar = {
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.my_account),
                    style = AppTheme.typography.pageTitle,
                    color = AppTheme.colorScheme.textSecondary
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .clip(ShapeTop15)
                .background(AppTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.login_to_account),
                style = AppTheme.typography.title.copy(
                    color = AppTheme.colorScheme.textPrimary
                )
            )
            FilledButton(
                modifier = Modifier
                    .padding(top = 25.dp, start = 15.dp, end = 15.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.authorize_via_campus),
                onClick = { onLoginPressed() }
            )
        }
    }
}

@Composable
fun ParticipationsPage(
    participationsList: List<Participation>,
    isCanEdit: Boolean,
    onParticipationPressed: (Int) -> Unit = {},
    onDeletePressed: (Int) -> Unit = {}
) {
    var deleteDialogVisible by remember { mutableStateOf(false) }
    var isEditMode by remember { mutableStateOf(false) }
    var selectedParticipation by remember { mutableStateOf(0) }

    SIDialog(
        modifier = Modifier.clip(Shape20),
        visible = deleteDialogVisible,
        backgroundColor = AppTheme.colorScheme.background,
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.delete_participation),
                style = AppTheme.typography.title.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = AppTheme.colorScheme.textPrimary,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = stringResource(R.string.delete_participation_text),
                style = AppTheme.typography.bodyMedium,
                color = AppTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
        },
        onDismissRequest = { deleteDialogVisible = false },
        dismissButton = {
            TextButton(
                text = stringResource(R.string.cancel),
                contentColor = AppTheme.colorScheme.primary,
                onClick = { deleteDialogVisible = false }
            )
        },
        confirmButton = {
            TextButton(
                text = stringResource(R.string.confirm),
                contentColor = AppTheme.colorScheme.error,
                onClick = {
                    onDeletePressed(selectedParticipation)
                    deleteDialogVisible = false
                }
            )
        }
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 15.dp,
                end = 15.dp,
                top = 23.dp,
                bottom = 50.dp
            ),
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        items(participationsList) { participation ->
            ParticipationItem(
                participation = participation,
                onClick = { onParticipationPressed(participation.projectId) },
                onDeleteClick = {
                    selectedParticipation = participation.id
                    deleteDialogVisible = true
                },
                isEditMode = isEditMode
            )
        }
        if (isCanEdit && !isEditMode) {
            item {
                OutlineButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.edit_participations),
                    onClick = { isEditMode = true }
                )
            }
        }
        if (isEditMode) {
            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    FilledButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.save_changes),
                        onClick = { isEditMode = false }
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(84.dp))
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@Composable
fun ProfilePage(candidate: Candidate?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 15.dp,
                end = 15.dp,
                top = 23.dp,
                bottom = 50.dp
            ),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.contact_info),
                style = AppTheme.typography.title.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        item {
            InfoBlock(
                title = stringResource(R.string.email),
                description = candidate?.email ?: ""
            )
        }
        item {
            InfoBlock(
                title = stringResource(R.string.phone_number),
                description = candidate?.phone?.ifBlank {
                    stringResource(R.string.not_specified)
                } ?: ""
            )
        }
        item {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = HalfGray
            )
        }
        item {
            Text(
                text = stringResource(R.string.additional_information),
                style = AppTheme.typography.title.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        item {
            InfoBlock(
                title = stringResource(R.string.training_group),
                description = candidate?.trainingGroup ?: ""
            )
        }
        item {
            InfoBlock(
                title = stringResource(R.string.course),
                description = candidate?.course.toString()
            )
        }
        item {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = HalfGray
            )
        }
    }
}

@Composable
fun ProjectsPage(
    projectsList: MutableList<Project>?,
    onProjectPressed: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.height(25.dp))
        }
        if (projectsList?.isNotEmpty() == true) {
            items(projectsList) { project ->
                ProjectItem(
                    modifier = Modifier.fillMaxWidth(),
                    project = project,
                    onClick = { onProjectPressed(project.id) }
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(84.dp))
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@Preview
@Composable
fun PreviewLoginPage() {
    AppTheme {
        LoginPage()
    }
}

@Preview
@Composable
fun PreviewAuthorizedPage(@PreviewParameter(SampleCandidateProvider::class) candidate: Candidate) {
    AppTheme {
        AuthorizedPage(
            candidate = candidate,
            participationsList = listOf(),
            projectsList = mutableListOf()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewParticipationsPage(
    @PreviewParameter(SampleParticipationProvider::class) participation: Participation
) {
    AppTheme {
        ParticipationsPage(participationsList = listOf(participation), isCanEdit = true)
    }
}
