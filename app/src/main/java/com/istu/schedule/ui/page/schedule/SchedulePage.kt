package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.istu.schedule.R
import com.istu.schedule.ui.components.calendar.HorizontalCalendar
import com.istu.schedule.ui.fonts.interFamily
import com.istu.schedule.ui.theme.Green
import com.istu.schedule.ui.theme.GreenContainer
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.util.collectAsStateValue
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SchedulePage(
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val scheduleUiState = viewModel.scheduleUiState.collectAsStateValue()

    val scheduleList by viewModel.scheduleList.observeAsState(initial = emptyList())

    val weeksList by viewModel.weeksList.observeAsState(initial = emptyList())
    val currentDate by viewModel.currentDate.observeAsState(initial = LocalDate.now())
    val selectedDate by viewModel.selectedDate.observeAsState(initial = LocalDate.now())

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

    LaunchedEffect(selectedDate) {
        viewModel.getSchedule()
    }

    // LaunchedEffect(startOfListReached) {
    //     viewModel.addWeekForward()
    // }

    LaunchedEffect(endOfListReached) {
        viewModel.addWeekBackward()
    }

    BackdropScaffold(
        modifier = Modifier.statusBarsPadding(),
        appBar = {
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.title_schedule),
                    style = MaterialTheme.typography.headlineMedium
                )
                if (scheduleUiState.userDescription != null) {
                    Text(
                        text = scheduleUiState.userDescription,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        },
        backLayerBackgroundColor = MaterialTheme.colorScheme.primary,
        backLayerContent = {
            HorizontalCalendar(
                weeksList = weeksList,
                currentDate = currentDate,
                selectedDate = selectedDate,
                calendarState = scheduleUiState.calendarState,
                onSelect = {
                    viewModel.selectDate(it)
                }
            )
        },
        frontLayerBackgroundColor = MaterialTheme.colorScheme.background,
        frontLayerContent = {
            LazyColumn(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                if (scheduleList.any()) {
                    scheduleList.first().lessons.forEach {
                        item {
                            val currentDateTime = LocalDateTime.now()

                            ScheduleCard(
                                currentDateTime = currentDateTime,
                                lesson = it,
                                lessonDate = scheduleList.first().date
                            )
                        }
                        it.breakTimeAfter?.let {
                            item {
                                BreakTime(stringBreakTime = it)
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
    BreakTime(stringBreakTime = "02:30:00")
}

@Composable
@Preview(showBackground = true)
fun SchedulePagePreview() {
    SchedulePage()
}
