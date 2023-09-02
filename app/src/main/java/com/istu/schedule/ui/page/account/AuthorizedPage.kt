package com.istu.schedule.ui.page.account

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Candidate
import com.istu.schedule.domain.model.projfair.Participation
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.domain.model.projfair.SampleCandidateProvider
import com.istu.schedule.domain.model.projfair.SampleParticipationProvider
import com.istu.schedule.domain.model.projfair.SampleProjectProvider
import com.istu.schedule.ui.components.base.CustomIndicator
import com.istu.schedule.ui.components.base.InfoBlock
import com.istu.schedule.ui.components.base.LoadingPanel
import com.istu.schedule.ui.components.base.SIDialog
import com.istu.schedule.ui.components.base.SIExtensibleVisibilityFadeOnly
import com.istu.schedule.ui.components.base.SIScrollableTabRow
import com.istu.schedule.ui.components.base.SITabPosition
import com.istu.schedule.ui.components.base.button.FilledButton
import com.istu.schedule.ui.components.base.button.OutlineButton
import com.istu.schedule.ui.components.base.button.TextButton
import com.istu.schedule.ui.components.projfair.ParticipationItem
import com.istu.schedule.ui.components.projfair.ProjectItem
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.HalfGray
import com.istu.schedule.ui.theme.Shape20
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.util.NavDestinations
import kotlinx.coroutines.launch

@Composable
fun AuthorizedPage(
    navController: NavController,
    candidate: Candidate?,
    viewModel: AccountViewModel
) {
    val isParticipationsLoaded by viewModel.isParticipationsLoaded.observeAsState(initial = false)
    val isProjectsLoaded by viewModel.isProjectsLoaded.observeAsState(initial = false)
    val participationsList by viewModel.participationsList.observeAsState(initial = emptyList())
    val projectsList by viewModel.projectsList.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchCandidateInfo()
        viewModel.fetchParticipationsList()
    }

    AuthorizedPage(
        candidate = candidate,
        isParticipationsLoaded = isParticipationsLoaded,
        isProjectsLoaded = isProjectsLoaded,
        participationsList = participationsList.distinctBy { participation -> participation.id },
        projectsList = projectsList.toMutableList(),
        onProjectPressed = {
            navController.navigate(
                "${NavDestinations.PROJECT}/$it/false"
            )
        },
        onParticipationPressed = {
            navController.navigate(
                "${NavDestinations.PROJECT}/$it/false"
            )
        },
        onDeletePressed = {
            viewModel.deleteParticipation(it)
        },
        onLogoutPressed = {
            viewModel.logout()
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthorizedPage(
    candidate: Candidate?,
    isParticipationsLoaded: Boolean,
    isProjectsLoaded: Boolean,
    participationsList: List<Participation>,
    projectsList: MutableList<Project>,
    onProjectPressed: (Int) -> Unit = {},
    onParticipationPressed: (Int) -> Unit = {},
    onDeletePressed: (Int) -> Unit = {},
    onLogoutPressed: () -> Unit = {}
) {
    var logoutDialogVisible by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val pages = listOf(
        stringResource(R.string.my_profile),
        stringResource(R.string.my_participations),
        stringResource(R.string.my_projects)
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { pages.count() }

    val indicator = @Composable { tabPositions: List<SITabPosition> ->
        CustomIndicator(tabPositions, pagerState)
    }

    SIDialog(
        modifier = Modifier.clip(Shape20),
        visible = logoutDialogVisible,
        backgroundColor = AppTheme.colorScheme.backgroundSecondary,
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.logout_text),
                style = AppTheme.typography.title.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = AppTheme.colorScheme.textPrimary,
                textAlign = TextAlign.Center
            )
        },
        onDismissRequest = { logoutDialogVisible = false },
        dismissButton = {
            TextButton(
                text = stringResource(R.string.cancel),
                contentColor = AppTheme.colorScheme.primary,
                onClick = { logoutDialogVisible = false }
            )
        },
        confirmButton = {
            TextButton(
                text = stringResource(R.string.logout),
                contentColor = AppTheme.colorScheme.error,
                onClick = {
                    onLogoutPressed()
                    logoutDialogVisible = false
                }
            )
        }
    )

    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = {
            Column(modifier = Modifier.statusBarsPadding()) {
                TopBar(title = stringResource(id = R.string.my_account))
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
                .background(AppTheme.colorScheme.backgroundSecondary)
        ) {
            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> {
                        ProfilePage(candidate = candidate)
                    }

                    1 -> {
                        ParticipationsPage(
                            participationsList = participationsList,
                            isCanEdit = candidate?.canSendParticipations == 1,
                            isParticipationsLoaded = isParticipationsLoaded,
                            onParticipationPressed = onParticipationPressed,
                            onDeletePressed = onDeletePressed
                        )
                    }

                    2 -> {
                        ProjectsPage(
                            projectsList = projectsList,
                            isProjectsLoaded = isProjectsLoaded,
                            onProjectPressed = onProjectPressed
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ParticipationsPage(
    participationsList: List<Participation>,
    isCanEdit: Boolean,
    isParticipationsLoaded: Boolean,
    onParticipationPressed: (Int) -> Unit = {},
    onDeletePressed: (Int) -> Unit = {}
) {
    var deleteDialogVisible by remember { mutableStateOf(false) }
    var isEditMode by remember { mutableStateOf(false) }
    var selectedParticipation by remember { mutableIntStateOf(0) }

    SIDialog(
        modifier = Modifier.clip(Shape20),
        visible = deleteDialogVisible,
        backgroundColor = AppTheme.colorScheme.backgroundSecondary,
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

    LoadingPanel(!isParticipationsLoaded)

    SIExtensibleVisibilityFadeOnly(
        visible = participationsList.isEmpty() && isParticipationsLoaded
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = 15.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.participations_not_found),
                style = AppTheme.typography.title.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = AppTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                text = stringResource(R.string.didnt_applied_participations),
                style = AppTheme.typography.subtitle,
                color = AppTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
        }
    }

    if (participationsList.isNotEmpty() && isParticipationsLoaded) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(9.dp),
            contentPadding = PaddingValues(
                start = 15.dp,
                end = 15.dp,
                top = 25.dp
            )
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
}

@Composable
fun ProfilePage(candidate: Candidate?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 15.dp,
                end = 15.dp,
                top = 23.dp
            ),
        verticalArrangement = Arrangement.spacedBy(20.dp)
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
                title = stringResource(R.string.fullname),
                description = candidate?.fio ?: ""
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
            HorizontalDivider(
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
            HorizontalDivider(
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
    isProjectsLoaded: Boolean,
    onProjectPressed: (Int) -> Unit
) {
    val isProjectsListEmpty = projectsList?.isEmpty()
    Box {
        SIExtensibleVisibilityFadeOnly(visible = isProjectsListEmpty == true && isProjectsLoaded) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(
                    space = 15.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.projects_not_found),
                    style = AppTheme.typography.title.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = AppTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    text = stringResource(R.string.didnt_participate_in_any_projects),
                    style = AppTheme.typography.subtitle,
                    color = AppTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
            }
        }
        SIExtensibleVisibilityFadeOnly(visible = isProjectsListEmpty != true && isProjectsLoaded) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = 25.dp,
                    start = 15.dp,
                    end = 15.dp
                ),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                if (isProjectsListEmpty != true) {
                    items(projectsList!!) { project ->
                        ProjectItem(
                            modifier = Modifier.fillMaxWidth(),
                            project = project
                        ) { onProjectPressed(project.id) }
                    }
                    item {
                        Spacer(modifier = Modifier.height(84.dp))
                        Spacer(
                            modifier = Modifier.windowInsetsBottomHeight(
                                WindowInsets.navigationBars
                            )
                        )
                    }
                }
            }
        }
        LoadingPanel(!isProjectsLoaded)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewParticipationsPage(
    @PreviewParameter(SampleParticipationProvider::class) participation: Participation
) {
    AppTheme {
        ParticipationsPage(
            participationsList = listOf(participation, participation, participation),
            isCanEdit = true,
            isParticipationsLoaded = true
        )
    }
}

@Preview
@Composable
fun PreviewAuthorizedPage(@PreviewParameter(SampleCandidateProvider::class) candidate: Candidate) {
    AppTheme {
        AuthorizedPage(
            candidate = candidate,
            isParticipationsLoaded = true,
            isProjectsLoaded = true,
            participationsList = listOf(),
            projectsList = mutableListOf()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProjectsPage(
    @PreviewParameter(SampleProjectProvider::class) project: Project
) {
    AppTheme {
        ProjectsPage(
            projectsList = mutableListOf(project, project, project),
            isProjectsLoaded = true,
            onProjectPressed = {}
        )
    }
}
