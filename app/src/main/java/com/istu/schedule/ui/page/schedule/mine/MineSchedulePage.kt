package com.istu.schedule.ui.page.schedule.mine

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.istu.schedule.data.model.Week
import com.istu.schedule.domain.model.schedule.SampleStudyDayProvider
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.ui.components.base.NoInternetPanel
import com.istu.schedule.ui.page.schedule.ScheduleContent
import com.istu.schedule.ui.page.schedule.ScheduleList
import com.istu.schedule.ui.page.schedule.ScheduleListIsLoading
import com.istu.schedule.ui.page.schedule.UserNotBindPlaceholder
import com.istu.schedule.ui.page.schedule.WeekendPlaceholder
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.util.NavDestinations
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun MineSchedulePage(
    navController: NavHostController,
    viewModel: MineScheduleViewModel = hiltViewModel(),
) {
    val schedule = viewModel.schedule

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val calendarState by viewModel.calendarState.collectAsStateWithLifecycle()

    val weeksList by viewModel.weeksList.observeAsState(initial = emptyList())
    val currentDate by viewModel.currentDate.collectAsStateWithLifecycle()
    val currentDateTime by viewModel.currentDateTime.collectAsStateWithLifecycle()
    val selectedDate by viewModel.selectedDate.observeAsState(initial = LocalDate.now())

    val startOfListReached by remember {
        derivedStateOf {
            calendarState.canScrollBackward
        }
    }

    val endOfListReached by remember {
        derivedStateOf {
            calendarState.canScrollForward
        }
    }

    LaunchedEffect(Unit) {
        val currentWeek = weeksList.first { it.days.contains(LocalDate.now()) }
        calendarState.scrollToItem(weeksList.indexOf(currentWeek))
    }

    LaunchedEffect(navController) {
        viewModel.updateUserInformation()
    }

    LaunchedEffect(startOfListReached) {
        viewModel.addWeekForward()
        calendarState.scrollToItem(
            index = calendarState.firstVisibleItemIndex + 1,
        )
    }

    LaunchedEffect(endOfListReached) {
        viewModel.addWeekBackward()
    }

    MineSchedulePage(
        uiState = uiState,
        calendarState = calendarState,
        weeksList = weeksList,
        currentDate = currentDate,
        currentDateTime = currentDateTime,
        selectedDate = selectedDate,
        studyDay = schedule,
        onDateSelect = { viewModel.selectDate(it) },
        onSearchButtonClick = { navController.navigate(NavDestinations.SEARCH_SCHEDULE) },
        onSetupScheduleClick = { navController.navigate(NavDestinations.SETTING_SCHEDULE) },
        spacer = {
             Spacer(
                 modifier = Modifier.windowInsetsBottomHeight(
                     WindowInsets.navigationBars
                 )
             )
        },
    )
}

@Composable
fun MineSchedulePage(
    uiState: MineScheduleUiState,
    calendarState: LazyListState,
    weeksList: List<Week>,
    currentDate: LocalDate = LocalDate.now(),
    currentDateTime: LocalDateTime,
    selectedDate: LocalDate,
    studyDay: StudyDay? = null,
    onDateSelect: (LocalDate) -> Unit,
    onSearchButtonClick: () -> Unit,
    onSetupScheduleClick: () -> Unit,
    spacer: (@Composable () -> Unit)? = null,
) {
    ScheduleContent(
        subtitle = uiState.description,
        calendarState = calendarState,
        weeksList = weeksList,
        currentDate = currentDate,
        selectedDate = selectedDate,
        onSearchButtonClick = onSearchButtonClick,
        onDateSelect = onDateSelect,
        content = {
            Crossfade(
                targetState = uiState,
                label = "",
            ) { targetState ->
                when (targetState) {
                    is MineScheduleUiState.NoInternetConnection -> NoInternetPanel(spacer = spacer)
                    MineScheduleUiState.UnknownUser -> {
                        UserNotBindPlaceholder(
                            onSetupScheduleClick = onSetupScheduleClick,
                            spacer = spacer,
                        )
                    }
                    is MineScheduleUiState.OnLoading -> {
                        ScheduleListIsLoading(spacer = spacer)
                    }
                    is MineScheduleUiState.Schedule -> {
                        studyDay?.let {
                            ScheduleList(
                                currentDateTime = currentDateTime,
                                studyDay = studyDay,
                                spacer = spacer,
                            )
                        }
                    }
                    is MineScheduleUiState.Weekend -> {
                        WeekendPlaceholder(
                            currentDate = currentDate,
                            selectedDate = selectedDate,
                            spacer = spacer,
                        )
                    }
                    is MineScheduleUiState.Error -> Unit // TODO: processing error state
                }
            }
        },
    )
}

@Composable
@Preview(showBackground = true, name = "Is loading", group = "Mine schedule")
fun IsLoadingMineSchedulePreview() {
    AppTheme {
        MineSchedulePage(
            uiState = MineScheduleUiState.OnLoading("ИСТб-20-3"),
            calendarState = rememberLazyListState(),
            weeksList = listOf(
                Week(startDayOfWeek = LocalDate.of(2023, 11, 6)),
            ),
            currentDateTime = LocalDateTime.of(
                LocalDate.of(2023, 11, 10),
                LocalTime.now(),
            ),
            selectedDate = LocalDate.of(2023, 11, 8),
            onDateSelect = { },
            onSearchButtonClick = { },
            onSetupScheduleClick = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Schedule list", group = "Mine schedule")
fun ScheduleListMineSchedulePreview(
    @PreviewParameter(SampleStudyDayProvider::class) studyDay: StudyDay,
) {
    AppTheme {
        MineSchedulePage(
            uiState = MineScheduleUiState.Schedule("ИСТб-20-3"),
            calendarState = rememberLazyListState(),
            weeksList = listOf(
                Week(startDayOfWeek = LocalDate.of(2023, 11, 6)),
            ),
            currentDateTime = LocalDateTime.of(
                LocalDate.of(2023, 11, 8),
                LocalTime.of(13, 58),
            ),
            selectedDate = LocalDate.of(2023, 11, 8),
            studyDay = studyDay,
            onDateSelect = { },
            onSearchButtonClick = { },
            onSetupScheduleClick = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "No internet connection", group = "Blanks")
fun NoInternetConnectionMineSchedulePreview() {
    AppTheme {
        MineSchedulePage(
            uiState = MineScheduleUiState.NoInternetConnection("ИСТб-20-3"),
            calendarState = rememberLazyListState(),
            weeksList = listOf(
                Week(startDayOfWeek = LocalDate.of(2023, 11, 6)),
            ),
            currentDateTime = LocalDateTime.of(
                LocalDate.of(2023, 11, 10),
                LocalTime.now(),
            ),
            selectedDate = LocalDate.of(2023, 11, 8),
            onDateSelect = { },
            onSearchButtonClick = { },
            onSetupScheduleClick = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "User not binded", group = "Blanks")
fun UserNotBindMineSchedulePreview() {
    AppTheme {
        MineSchedulePage(
            uiState = MineScheduleUiState.UnknownUser,
            calendarState = rememberLazyListState(),
            weeksList = listOf(
                Week(startDayOfWeek = LocalDate.of(2023, 11, 6)),
            ),
            currentDateTime = LocalDateTime.of(
                LocalDate.of(2023, 11, 10),
                LocalTime.now(),
            ),
            selectedDate = LocalDate.of(2023, 11, 8),
            onDateSelect = { },
            onSearchButtonClick = { },
            onSetupScheduleClick = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Weekend not binded", group = "Blanks")
fun WeekendMineSchedulePreview(
    @PreviewParameter(SampleStudyDayProvider::class) studyDay: StudyDay,
) {
    AppTheme {
        MineSchedulePage(
            uiState = MineScheduleUiState.Weekend("ИСТб-20-3"),
            calendarState = rememberLazyListState(),
            weeksList = listOf(
                Week(startDayOfWeek = LocalDate.of(2023, 11, 6)),
            ),
            currentDateTime = LocalDateTime.of(
                LocalDate.of(2023, 11, 10),
                LocalTime.now(),
            ),
            selectedDate = LocalDate.of(2023, 11, 11),
            onDateSelect = { },
            onSearchButtonClick = { },
            onSetupScheduleClick = { },
        )
    }
}
