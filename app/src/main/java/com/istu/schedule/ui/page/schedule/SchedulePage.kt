package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.AppComposable
import com.istu.schedule.ui.components.calendar.HorizontalCalendar
import com.istu.schedule.util.collectAsStateValue
import java.time.LocalDate
import java.time.LocalDateTime

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

    AppComposable(
        viewModel = viewModel,
        content = {
            BackdropScaffold(
                modifier = Modifier.statusBarsPadding(),
                appBar = {
                    Column(
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.title_schedule),
                            style = MaterialTheme.typography.headlineMedium,
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
                frontLayerBackgroundColor = MaterialTheme.colorScheme.surface,
                frontLayerContent = {
                    LazyColumn(
                        modifier = Modifier
                            .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        if (scheduleList.any()) {
                            items(scheduleList.first().lessons) { lesson ->
                                val currentDateTime = LocalDateTime.now()

                                ScheduleCard(
                                    currentDateTime = currentDateTime,
                                    lesson = lesson,
                                    lessonDate = scheduleList.first().date
                                )
                            }
                            item {
                                Spacer(modifier = Modifier.height(128.dp))
                                Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
                            }
                        }
                    }
                },
            )
        },
    )
}

@Composable
@Preview(showBackground = true)
fun SchedulePagePreview() {
    SchedulePage()
}