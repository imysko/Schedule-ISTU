package com.istu.schedule.ui.page.schedule.found

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.istu.schedule.R
import com.istu.schedule.data.enums.ScheduleType
import com.istu.schedule.data.model.Week
import com.istu.schedule.domain.model.schedule.SampleStudyDayProvider
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.ui.components.base.NoInternetPanel
import com.istu.schedule.ui.page.schedule.ScheduleContent
import com.istu.schedule.ui.page.schedule.ScheduleList
import com.istu.schedule.ui.page.schedule.ScheduleListIsLoading
import com.istu.schedule.ui.page.schedule.WeekendPlaceholder
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.util.NavDestinations
import com.istu.schedule.ui.util.VibrationManager
import com.istu.schedule.util.collectAsStateValue
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun FoundSchedulePage(
    navController: NavHostController,
    title: String?,
    viewModel: FoundScheduleViewModel = hiltViewModel(),
) {
    val schedule = viewModel.schedule

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val calendarState = viewModel.calendarState.collectAsStateValue()

    val weeksList by viewModel.weeksList.observeAsState(initial = emptyList())
    val currentDate by viewModel.currentDate.collectAsStateWithLifecycle()
    val currentDateTime by viewModel.currentDateTime.collectAsStateWithLifecycle()
    val selectedDate by viewModel.selectedDate.observeAsState(initial = LocalDate.now())

    val localContext = LocalContext.current

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

    LaunchedEffect(startOfListReached) {
        viewModel.addWeekForward()
        calendarState.scrollToItem(
            index = calendarState.firstVisibleItemIndex + 1,
        )
    }

    LaunchedEffect(endOfListReached) {
        viewModel.addWeekBackward()
    }

    FoundSchedulePage(
        title = title,
        uiState = uiState,
        calendarState = calendarState,
        weeksList = weeksList,
        currentDate = currentDate,
        currentDateTime = currentDateTime,
        selectedDate = selectedDate,
        studyDay = schedule,
        onDateSelect = { viewModel.selectDate(it) },
        onSearchButtonClick = { navController.navigate(NavDestinations.SEARCH_SCHEDULE) },
        onBackClick = { navController.popBackStack() },
        onDropdownItemClick = { scheduleType, id, description ->
            navController.navigate(
                "${NavDestinations.FOUND_SCHEDULE}/${scheduleType}/${id}/${description}"
            )
        },
        onLongPressVibrate = { viewModel.vibrationManager.vibrate(
                context = localContext,
                effect = VibrationManager.LongPress
            )
        },
    )
}

@Composable
fun FoundSchedulePage(
    title: String?,
    uiState: FoundScheduleUiState,
    calendarState: LazyListState,
    weeksList: List<Week>,
    currentDate: LocalDate = LocalDate.now(),
    currentDateTime: LocalDateTime,
    selectedDate: LocalDate,
    studyDay: StudyDay? = null,
    onDateSelect: (LocalDate) -> Unit,
    onSearchButtonClick: () -> Unit,
    onBackClick: () -> Unit,
    onDropdownItemClick: (ScheduleType, Int, String) -> Unit,
    onLongPressVibrate: (() -> Unit)? = null,
) {
    ScheduleContent(
        subtitle = title,
        calendarState = calendarState,
        weeksList = weeksList,
        currentDate = currentDate,
        selectedDate = selectedDate,
        onSearchButtonClick = onSearchButtonClick,
        onDateSelect = onDateSelect,
        backNavigationButton = {
            Box(
                modifier = Modifier.padding(top = 20.dp, start = 15.dp, end = 15.dp)
            ) {
                Row(
                    modifier = Modifier
                        .clip(Shape10)
                        .clickable(onClick = onBackClick)
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(9.dp),
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = stringResource(R.string.back_to_schedule_search),
                        tint = AppTheme.colorScheme.secondary,
                    )
                    Text(
                        text = stringResource(R.string.back_to_schedule_search),
                        style = AppTheme.typography.bodyMedium.copy(
                            color = AppTheme.colorScheme.secondary,
                            fontWeight = FontWeight.SemiBold,
                        ),
                    )
                }
            }
        },
        content = {
            Crossfade(
                targetState = uiState,
                label = "",
            ) { targetState ->
                when (targetState) {
                    FoundScheduleUiState.NoInternetConnection -> NoInternetPanel()
                    FoundScheduleUiState.OnLoading -> {
                        ScheduleListIsLoading()
                    }
                    FoundScheduleUiState.Schedule -> {
                        studyDay?.let {
                            ScheduleList(
                                currentDateTime = currentDateTime,
                                studyDay = studyDay,
                                onDropdownItemClick = { scheduleType, id, title ->
                                    onDropdownItemClick(scheduleType, id, title)
                                },
                                onLongPressVibrate = onLongPressVibrate,
                            )
                        }
                    }
                    FoundScheduleUiState.Weekend -> {
                        WeekendPlaceholder(
                            currentDate = currentDate,
                            selectedDate = selectedDate,
                        )
                    }
                    is FoundScheduleUiState.Error -> Unit // TODO: processing error state
                }
            }
        },
    )
}

@Composable
@Preview(showBackground = true, name = "Is loading", group = "Found schedule")
fun IsLoadingFoundSchedulePreview() {
    AppTheme {
        FoundSchedulePage(
            title = "Аршинский Вадим Леонидович",
            uiState = FoundScheduleUiState.OnLoading,
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
            onBackClick = { },
            onDropdownItemClick = { _, _, _ -> },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Schedule list", group = "Found schedule")
fun ScheduleListFoundSchedulePreview(
    @PreviewParameter(SampleStudyDayProvider::class) studyDay: StudyDay,
) {
    AppTheme {
        FoundSchedulePage(

            title = "Аршинский Вадим Леонидович",
            uiState = FoundScheduleUiState.Schedule,
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
            onBackClick = { },
            onDropdownItemClick = { _, _, _ -> },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "No internet connection", group = "Blanks")
fun NoInternetConnectionFoundSchedulePreview() {
    AppTheme {
        FoundSchedulePage(
            title = "Аршинский Вадим Леонидович",
            uiState = FoundScheduleUiState.NoInternetConnection,
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
            onBackClick = { },
            onDropdownItemClick = { _, _, _ -> },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Weekend not binded", group = "Blanks")
fun WeekendFoundSchedulePreview(
    @PreviewParameter(SampleStudyDayProvider::class) studyDay: StudyDay,
) {
    AppTheme {
        FoundSchedulePage(
            title = "Аршинский Вадим Леонидович",
            uiState = FoundScheduleUiState.Weekend,
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
            onBackClick = { },
            onDropdownItemClick = { _, _, _ -> },
        )
    }
}
