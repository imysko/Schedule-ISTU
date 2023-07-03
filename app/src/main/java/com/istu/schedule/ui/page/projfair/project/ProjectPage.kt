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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Participation
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.ui.components.base.CustomIndicator
import com.istu.schedule.ui.components.base.SIChip
import com.istu.schedule.ui.components.base.SIScrollableTabRow
import com.istu.schedule.ui.components.base.SITabPosition
import com.istu.schedule.ui.components.base.TwoColumnText
import com.istu.schedule.ui.components.base.button.FilledButton
import com.istu.schedule.ui.icons.People
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.HalfGray
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.util.toProjectDifficulty
import java.text.DateFormat
import java.text.SimpleDateFormat
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProjectPage(
    projectId: Int,
    navController: NavController,
    viewModel: ProjectViewModel = hiltViewModel()
) {
    val project by viewModel.project.observeAsState(initial = null)
    viewModel.getProjectById(projectId)

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val pages = listOf(
        stringResource(R.string.about_project),
        stringResource(R.string.list_of_particpations)
    )
    val indicator = @Composable { tabPositions: List<SITabPosition> ->
        CustomIndicator(tabPositions, pagerState)
    }

    Scaffold(
        containerColor = AppTheme.colorScheme.primary,
        topBar = {
            Column(modifier = Modifier.statusBarsPadding()) {
                Text(
                    modifier = Modifier.padding(15.dp),
                    text = stringResource(id = R.string.projfair),
                    style = AppTheme.typography.pageTitle,
                    color = AppTheme.colorScheme.textSecondary
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
    ) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .clip(ShapeTop15)
                .background(AppTheme.colorScheme.background)
                .padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 23.dp,
                    bottom = 50.dp
                )
        ) {
            Box(
                modifier = Modifier.clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    navController.popBackStack()
                }
            ) {
                Row(
                    modifier = Modifier.padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = stringResource(R.string.back),
                        tint = AppTheme.colorScheme.secondary
                    )
                    Text(
                        modifier = Modifier.padding(start = 9.dp),
                        text = stringResource(R.string.back_to_list),
                        style = AppTheme.typography.bodyMedium.copy(
                            color = AppTheme.colorScheme.secondary,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
            project?.let { project ->
                Text(
                    text = project.title,
                    style = AppTheme.typography.title.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                HorizontalPager(
                    pageCount = pages.size,
                    state = pagerState
                ) { page ->
                    when (page) {
                        0 -> ProjectInfo(project)
                        1 -> ProjectParticipations(project)
                    }
                }
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
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectInfo(project: Project) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 23.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 22.dp),
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
                            .padding(24.dp, 7.dp)
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
            Divider(
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
                value = project.supervisorsNames
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
            Divider(
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
            Divider(
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
        if (project.state.id == 1) {
            item {
                FilledButton(
                    modifier = Modifier
                        .padding(top = 13.dp)
                        .fillMaxWidth()
                        .height(42.dp),
                    text = stringResource(R.string.send_participation)
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
fun ProjectParticipations(project: Project) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 23.dp)
            .fillMaxHeight()
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
            Spacer(
                modifier = Modifier
                    .height(64.dp)
                    .windowInsetsBottomHeight(WindowInsets.navigationBars)
            )
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun ParticipationInProject(index: Int, participation: Participation) {
    val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
    Row(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(
            modifier = Modifier
                .weight(0.05f)
                .padding(horizontal = 2.dp),
            text = index.toString(),
            style = AppTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
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
