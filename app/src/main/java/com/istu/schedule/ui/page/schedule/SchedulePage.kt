package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.istu.schedule.R
import com.istu.schedule.data.model.Week
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.ui.components.base.button.FilledButton
import com.istu.schedule.ui.components.calendar.HorizontalCalendar
import com.istu.schedule.ui.fonts.interFamily
import com.istu.schedule.ui.theme.Green
import com.istu.schedule.ui.theme.GreenContainer
import com.istu.schedule.ui.theme.ScheduleISTUTheme
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.util.NavDestinations
import com.istu.schedule.util.collectAsStateValue
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun SchedulePage(
    navController: NavHostController,
    viewModel: ScheduleViewModel = hiltViewModel(),
) {
    val isLoading by viewModel.loading.observeAsState(initial = false)

    val scheduleUiState = viewModel.scheduleUiState.collectAsStateValue()

    val weeksList by viewModel.weeksList.observeAsState(initial = emptyList())
    val currentDate by viewModel.currentDate.observeAsState(initial = LocalDate.now())
    val selectedDate by viewModel.selectedDate.observeAsState(initial = LocalDate.now())

    val schedule by viewModel.schedule.observeAsState()

    val startOfListReached by remember {
        derivedStateOf {
            scheduleUiState.calendarState.canScrollBackward
        }
    }

    val endOfListReached by remember {
        derivedStateOf {
            scheduleUiState.calendarState.canScrollForward
        }
    }

    LaunchedEffect(Unit) {
        scheduleUiState.calendarState.scrollToItem(1)
    }

    LaunchedEffect(navController) {
        viewModel.updateUserInformation()
    }

    LaunchedEffect(selectedDate) {
        viewModel.getSchedule()
    }

    LaunchedEffect(startOfListReached) {
        viewModel.addWeekForward()
        scheduleUiState.calendarState.scrollToItem(
            index = scheduleUiState.calendarState.firstVisibleItemIndex + 1
        )
    }

    LaunchedEffect(endOfListReached) {
        viewModel.addWeekBackward()
    }
    
    SchedulePage(
        scheduleUiState = scheduleUiState,
        isLoading = isLoading,
        schedule = schedule,
        weeksList = weeksList,
        currentDate = currentDate,
        selectedDate = selectedDate,
        onDateSelect = { viewModel.selectDate(it) },
        onSetupScheduleClick = { navController.navigate(NavDestinations.BINDING_PAGE) },
    )
}

@Composable
fun SchedulePage(
    scheduleUiState: ScheduleUiState,
    isLoading: Boolean,
    schedule: StudyDay?,
    weeksList: List<Week>,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    onDateSelect: (LocalDate) -> Unit,
    onSetupScheduleClick: () -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(vertical = 15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                    Text(
                        text = stringResource(id = R.string.title_schedule),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    if (scheduleUiState.isUserBinded) {
                        Text(
                            text = scheduleUiState.userDescription!!,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }

                HorizontalCalendar(
                    weeksList = weeksList,
                    currentDate = currentDate,
                    selectedDate = selectedDate,
                    calendarState = scheduleUiState.calendarState,
                    onSelect = { onDateSelect(it) }
                )
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = it.calculateTopPadding())
                    .clip(ShapeTop15)
                    .background(MaterialTheme.colorScheme.background),
            ) {
                if (!scheduleUiState.isUserBinded) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.unknown_user),
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                        FilledButton(
                            modifier = Modifier
                                .padding(top = 25.dp)
                                .fillMaxWidth(),
                            text = stringResource(R.string.setup_schedule),
                            onClick = { onSetupScheduleClick() }
                        )
                        Spacer(modifier = Modifier.height(128.dp))
                        Spacer(
                            modifier = Modifier.windowInsetsBottomHeight(
                                WindowInsets.navigationBars
                            )
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 15.dp),
                        contentPadding = PaddingValues(top = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                    ) {
                        schedule?.let {
                            if (isLoading) {
                                items(3) {
                                    ScheduleCardPlaceHolder()
                                }
                            }
                            if (schedule.lessons.isEmpty()) {
                                item {
                                    Column(
                                        modifier = Modifier
                                            .fillParentMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.weekend),
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                color = MaterialTheme.colorScheme.onBackground
                                            )
                                        )
                                        Image(
                                            painter = painterResource(
                                                id = R.drawable.login_to_personal_account
                                            ),
                                            contentDescription = null,
                                        )
                                        Spacer(modifier = Modifier.height(128.dp))
                                        Spacer(
                                            modifier = Modifier.windowInsetsBottomHeight(
                                                WindowInsets.navigationBars
                                            )
                                        )
                                    }
                                }
                            }
                            schedule.lessons.forEach { lesson ->
                                item {
                                    val currentDateTime = LocalDateTime.now()

                                    ScheduleCard(
                                        currentDateTime = currentDateTime,
                                        lesson = lesson,
                                        lessonDate = schedule.date
                                    )
                                }
                                lesson.breakTimeAfter?.let {
                                    item {
                                        BreakTime(stringBreakTime = it)
                                    }
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

            }
        }
    )
}

@Composable
fun BreakTime(stringBreakTime: String) {
    Box(
        modifier = Modifier
            .background(
                color = GreenContainer,
                shape = Shape10
            )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.break_time),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = interFamily,
                color = Green
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                if (LocalTime.parse(stringBreakTime).hour > 0) {
                    Text(
                        text = pluralStringResource(
                            id = R.plurals.hours,
                            count = LocalTime.parse(stringBreakTime).hour,
                            LocalTime.parse(stringBreakTime).hour
                        ),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = interFamily,
                        color = Green
                    )
                }
                if (LocalTime.parse(stringBreakTime).minute > 0) {
                    Text(
                        text = pluralStringResource(
                            id = R.plurals.minutes,
                            count = LocalTime.parse(stringBreakTime).minute,
                            LocalTime.parse(stringBreakTime).minute
                        ),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = interFamily,
                        color = Green
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, locale = "ru")
fun BreakTimePreview() {
    ScheduleISTUTheme {
        BreakTime(stringBreakTime = "02:30:00")
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageLoadingPreview() {
    ScheduleISTUTheme {
        SchedulePage(
            scheduleUiState = ScheduleUiState(),
            isLoading = true,
            schedule = null,
            weeksList = listOf(
                Week(LocalDate.of(2023, 6, 12)),
            ),
            currentDate = LocalDate.now(),
            selectedDate = LocalDate.now().plusDays(2),
            onDateSelect = { },
            onSetupScheduleClick = { },
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageUnknownUserPreview() {
    ScheduleISTUTheme {
        SchedulePage(
            scheduleUiState = ScheduleUiState(),
            isLoading = false,
            schedule = StudyDay(
                date = "",
                lessons = emptyList(),
            ),
            weeksList = listOf(
                Week(LocalDate.of(2023, 6, 12)),
            ),
            currentDate = LocalDate.now(),
            selectedDate = LocalDate.now().plusDays(2),
            onDateSelect = { },
            onSetupScheduleClick = { },
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageWeekendPreview() {
    ScheduleISTUTheme {
        SchedulePage(
            scheduleUiState = ScheduleUiState(
                isUserBinded = true,
            ),
            isLoading = false,
            schedule = StudyDay(
                date = "",
                lessons = emptyList(),
            ),
            weeksList = listOf(
                Week(LocalDate.of(2023, 6, 12)),
            ),
            currentDate = LocalDate.now(),
            selectedDate = LocalDate.now().plusDays(2),
            onDateSelect = { },
            onSetupScheduleClick = { },
        )
    }
}
