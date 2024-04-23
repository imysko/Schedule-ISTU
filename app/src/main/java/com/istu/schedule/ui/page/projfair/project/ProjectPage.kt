package com.istu.schedule.ui.page.projfair.project

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.CustomIndicator
import com.istu.schedule.ui.components.base.LoadingPanel
import com.istu.schedule.ui.components.base.NoInternetPanel
import com.istu.schedule.ui.components.base.SIAnimatedVisibilityFadeOnly
import com.istu.schedule.ui.components.base.SIChip
import com.istu.schedule.ui.components.base.SIScrollableTabRow
import com.istu.schedule.ui.components.base.SITabPosition
import com.istu.schedule.ui.components.base.TwoColumnText
import com.istu.schedule.ui.components.base.button.FilledButton
import com.istu.schedule.ui.icons.People
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.HalfGray
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.ui.util.NavDestinations
import com.istu.schedule.ui.util.previewParameterProviders.SampleProjectPreviewParameterProvider
import com.istu.schedule.util.NetworkStatus
import com.istu.schedule.util.toProjectDifficulty
import java.text.DateFormat
import java.text.SimpleDateFormat
import kotlinx.coroutines.launch
import me.progneo.projfair.domain.model.Participation
import me.progneo.projfair.domain.model.Project

@Composable
fun ProjectPage(
    projectId: Int,
    canCreateParticipation: Boolean,
    navController: NavController,
    viewModel: ProjectViewModel = hiltViewModel()
) {
    val project by viewModel.project.observeAsState(initial = null)
    val participationList by viewModel.participationList.observeAsState(initial = emptyList())
    val networkStatus by viewModel.networkStatus.observeAsState(initial = NetworkStatus.Available)

    var participationExists by remember { mutableStateOf(false) }

    viewModel.fetchProjectById(projectId)
    viewModel.fetchParticipationList()

    LaunchedEffect(participationList) {
        participationExists =
            participationList.any { participation -> participation.projectId == projectId }
    }

    ProjectPage(
        project = project,
        canCreateParticipation = canCreateParticipation && !participationExists,
        networkStatus = networkStatus,
        onBackPressed = { navController.popBackStack() },
        onCreateParticipationPressed = {
            navController.navigate(
                "${NavDestinations.CREATE_PARTICIPATION}/$projectId"
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProjectPage(
    project: Project?,
    canCreateParticipation: Boolean,
    networkStatus: NetworkStatus,
    onBackPressed: () -> Unit = {},
    onCreateParticipationPressed: () -> Unit = {}
) {
    val pages = listOf(
        stringResource(R.string.about_project),
        stringResource(R.string.list_of_particpations)
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { pages.count() }

    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = {
            TopBar(
                pages = pages,
                pagerState = pagerState,
                onBackPressed = onBackPressed
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .clip(ShapeTop15)
                .background(AppTheme.colorScheme.backgroundSecondary)
                .fillMaxHeight()
        ) {
            SIAnimatedVisibilityFadeOnly(
                networkStatus == NetworkStatus.Unavailable && project == null
            ) {
                NoInternetPanel()
            }

            LoadingPanel(
                networkStatus == NetworkStatus.Available && project == null
            )

            SIAnimatedVisibilityFadeOnly(project != null) {
                HorizontalPager(state = pagerState) { page ->
                    when (page) {
                        0 -> ProjectInfo(
                            project = project!!,
                            canCreateParticipation = canCreateParticipation,
                            onCreateParticipationClick = { onCreateParticipationPressed() }
                        )

                        1 -> ProjectParticipations(project!!)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProjectInfo(
    project: Project,
    canCreateParticipation: Boolean,
    onCreateParticipationClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        item {
            Text(
                text = project.title,
                style = AppTheme.typography.title.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        text = stringResource(R.string.maximum_participants),
                        style = AppTheme.typography.bodyMedium
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            imageVector = Icons.People,
                            contentDescription = "People Icon",
                            tint = AppTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = project.places.toString(),
                            style = AppTheme.typography.subtitle.copy(
                                color = AppTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        text = stringResource(R.string.project_status),
                        style = AppTheme.typography.bodyMedium
                    )
                    Box(
                        modifier = Modifier
                            .border(
                                BorderStroke(
                                    width = 1.45.dp,
                                    when (project.state.id) {
                                        1 -> com.istu.schedule.ui.theme.Blue
                                        2 -> com.istu.schedule.ui.theme.Green
                                        5 -> com.istu.schedule.ui.theme.Cyan
                                        else -> AppTheme.colorScheme.secondary
                                    }
                                ),
                                RoundedCornerShape(72.dp)
                            )
                            .padding(24.dp, 9.dp)
                    ) {
                        Text(
                            text = project.state.state.uppercase(),
                            style = AppTheme.typography.bodySmall.copy(
                                color = when (project.state.id) {
                                    1 -> com.istu.schedule.ui.theme.Blue
                                    2 -> com.istu.schedule.ui.theme.Green
                                    5 -> com.istu.schedule.ui.theme.Cyan
                                    else -> AppTheme.colorScheme.secondary
                                },
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }
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
            TwoColumnText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp),
                key = stringResource(R.string.project_manager),
                value = project.supervisors.joinToString(separator = ", ") { it.supervisor.fio }
            )
        }
        item {
            TwoColumnText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp),
                key = stringResource(R.string.customer),
                value = project.customer
            )
        }
        item {
            TwoColumnText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp),
                key = stringResource(R.string.timeline),
                value = "${
                    DateFormat.getDateInstance(DateFormat.LONG).format(project.dateStart)
                } - ${DateFormat.getDateInstance(DateFormat.LONG).format(project.dateEnd)}"
            )
        }
        item {
            TwoColumnText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp),
                key = stringResource(R.string.difficulty),
                value = project.difficulty.toProjectDifficulty()
            )
        }
        item {
            TwoColumnText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp),
                key = stringResource(R.string.type_of_project),
                value = project.type.type
            )
        }
        item {
            TwoColumnText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 22.dp),
                key = stringResource(R.string.project_goal),
                value = project.goal
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
            TwoColumnText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp),
                key = stringResource(R.string.expected_result),
                value = project.productResult
            )
        }
        item {
            TwoColumnText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 22.dp),
                key = stringResource(R.string.requirements_for_participants),
                value = project.studyResult
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
            Column(modifier = Modifier.padding(vertical = 22.dp)) {
                Text(
                    style = AppTheme.typography.bodySmall,
                    text = stringResource(R.string.project_idea)
                )
                Spacer(Modifier.height(11.dp))
                Text(
                    style = AppTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    text = project.description
                )
            }
        }
        if (project.skills.isNotEmpty()) {
            item {
                Text(
                    style = AppTheme.typography.bodySmall,
                    text = stringResource(R.string.required_skills)
                )
                FlowRow(modifier = Modifier.padding(top = 15.dp)) {
                    project.skills.sortedBy { it.name.length }.forEach {
                        SIChip(
                            modifier = Modifier.padding(end = 6.dp, bottom = 9.dp),
                            text = it.name
                        )
                    }
                }
            }
        }
        if (project.state.id == 1 && canCreateParticipation) {
            item {
                FilledButton(
                    modifier = Modifier
                        .padding(top = 13.dp)
                        .fillMaxWidth()
                        .height(42.dp),
                    text = stringResource(R.string.send_participation),
                    onClick = { onCreateParticipationClick() }
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(64.dp))
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@Composable
private fun ProjectParticipations(project: Project) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        item {
            Row {
                Text(
                    modifier = Modifier
                        .weight(0.05f)
                        .padding(horizontal = 2.dp),
                    text = "№",
                    style = AppTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .weight(0.225f)
                        .padding(horizontal = 2.dp),
                    text = "ФИО",
                    style = AppTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .weight(0.150f)
                        .padding(horizontal = 2.dp),
                    text = "Группа",
                    style = AppTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .weight(0.080f)
                        .padding(horizontal = 2.dp),
                    text = "Приоритет",
                    style = AppTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .weight(0.205f)
                        .padding(horizontal = 2.dp),
                    text = "Дата подачи заявки",
                    style = AppTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End
                )
            }
        }
        itemsIndexed(
            project.participations.sortedWith(
                compareByDescending<Participation> { it.state.id }.thenBy { it.priority }
            )
        ) { index, participation ->
            ParticipationInProject(index + 1, participation)
        }
        item {
            Spacer(modifier = Modifier.height(64.dp))
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
private fun ParticipationInProject(index: Int, participation: Participation) {
    val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
    Row(modifier = Modifier.padding(vertical = 10.dp)) {
        Box(
            modifier = Modifier
                .weight(0.05f)
                .padding(horizontal = 2.dp)
                .clip(Shape10)
                .background(
                    if (participation.state.id == 2) {
                        AppTheme.colorScheme.success.copy(alpha = 0.3f)
                    } else {
                        Color.Transparent
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = index.toString(),
                style = AppTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
        Text(
            modifier = Modifier
                .weight(0.225f)
                .padding(horizontal = 2.dp),
            text = participation.candidate?.fio ?: "-",
            style = AppTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .weight(0.150f)
                .padding(horizontal = 2.dp),
            text = participation.candidate?.trainingGroup ?: "-",
            style = AppTheme.typography.labelMedium
        )
        Text(
            modifier = Modifier
                .weight(0.080f)
                .padding(horizontal = 2.dp),
            text = participation.priority.toString(),
            style = AppTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .weight(0.205f)
                .padding(horizontal = 2.dp),
            text = simpleDateFormat.format(participation.updatedAt),
            style = AppTheme.typography.labelMedium,
            textAlign = TextAlign.End
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TopBar(
    pages: List<String>,
    pagerState: PagerState,
    onBackPressed: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val indicator = @Composable { tabPositions: List<SITabPosition> ->
        CustomIndicator(tabPositions, pagerState)
    }

    Column(modifier = Modifier.statusBarsPadding()) {
        TopBar(
            title = stringResource(id = R.string.projfair),
            isShowBackButton = true,
            onBackClick = onBackPressed
        )
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
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = AppTheme.colorScheme.textSecondary,
                        text = title
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProjectPageLoading() {
    AppTheme {
        ProjectPage(
            project = null,
            networkStatus = NetworkStatus.Available,
            canCreateParticipation = true
        )
    }
}

@Preview
@Composable
fun PreviewProjectPageNoNetwork() {
    AppTheme {
        ProjectPage(
            project = null,
            networkStatus = NetworkStatus.Unavailable,
            canCreateParticipation = true
        )
    }
}

@Preview
@Composable
fun PreviewProjectAboutPage(
    @PreviewParameter(SampleProjectPreviewParameterProvider::class) project: Project
) {
    AppTheme {
        ProjectPage(
            project = project,
            networkStatus = NetworkStatus.Available,
            canCreateParticipation = true
        )
    }
}

@Preview(group = "Loaded", showBackground = true)
@Composable
fun PreviewProjectListOfParticipationPage(
    @PreviewParameter(SampleProjectPreviewParameterProvider::class) project: Project
) {
    AppTheme {
        ProjectParticipations(project)
    }
}
