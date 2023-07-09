package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.istu.schedule.data.model.Week
import com.istu.schedule.domain.model.schedule.SampleStudyDayProvider
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.ui.components.base.SIExtensibleVisibility
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.util.NavDestinations
import com.istu.schedule.util.collectAsStateValue
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun SchedulePage(
    navController: NavHostController,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val isLoading by viewModel.loading.observeAsState(initial = false)

    val scheduleUiState = viewModel.scheduleUiState.collectAsStateValue()

    val weeksList by viewModel.weeksList.observeAsState(initial = emptyList())
    val currentDateTime by viewModel.currentDateTime.collectAsStateWithLifecycle()
    val selectedDate by viewModel.selectedDate.observeAsState(initial = LocalDate.now())

    val schedule by viewModel.schedule.observeAsState()

    val searchedListsTips = viewModel.searchedListsTips.collectAsStateValue()

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
        studyDay = schedule,
        weeksList = weeksList,
        currentDateTime = currentDateTime,
        selectedDate = selectedDate,
        searchedListsTips = searchedListsTips,
        onSearchButtonClick = { viewModel.changeSearchBarVisibility() },
        onDateSelect = { viewModel.selectDate(it) },
        onValueInputDone = { viewModel.onValueInput(it) },
        onSetupScheduleClick = { navController.navigate(NavDestinations.BINDING) }
    )
}

@Composable
fun SchedulePage(
    scheduleUiState: ScheduleUiState,
    isLoading: Boolean,
    studyDay: StudyDay?,
    weeksList: List<Week>,
    currentDateTime: LocalDateTime,
    selectedDate: LocalDate,
    searchedListsTips: SearchedLists,
    onSearchButtonClick: () -> Unit,
    onDateSelect: (LocalDate) -> Unit,
    onValueInputDone: (String) -> Unit,
    onSetupScheduleClick: () -> Unit
) {
    Scaffold(
        containerColor = AppTheme.colorScheme.primary,
        topBar = {
            ScheduleTopBar(
                scheduleUiState = scheduleUiState,
                weeksList = weeksList,
                currentDate = currentDateTime.toLocalDate(),
                selectedDate = selectedDate,
                onSearchButtonClick = { onSearchButtonClick() },
                onDateSelect = { onDateSelect(it) },
                onValueInputDone = { onValueInputDone(it) }
            )
        },
        content = {
            SIExtensibleVisibility(scheduleUiState.isScheduleListVisible) {
                ScheduleContent(
                    paddingValues = it,
                    content = {
                        if (!scheduleUiState.isUserBinded) {
                            UserNotBindedPlaceholder(
                                onSetupScheduleClick = { onSetupScheduleClick() },
                                spacer = {
                                    Spacer(modifier = Modifier.height(128.dp))
                                    Spacer(
                                        modifier = Modifier.windowInsetsBottomHeight(
                                            WindowInsets.navigationBars
                                        )
                                    )
                                }
                            )
                        } else if (isLoading) {
                            ScheduleListIsLoading(
                                spacer = {
                                    Spacer(modifier = Modifier.height(128.dp))
                                    Spacer(
                                        modifier = Modifier.windowInsetsBottomHeight(
                                            WindowInsets.navigationBars
                                        )
                                    )
                                }
                            )
                        } else {
                            studyDay?.let { studyDay ->
                                if (studyDay.lessons.isEmpty()) {
                                    WeekendPlaceholder(
                                        spacer = {
                                            Spacer(modifier = Modifier.height(128.dp))
                                            Spacer(
                                                modifier = Modifier.windowInsetsBottomHeight(
                                                    WindowInsets.navigationBars
                                                )
                                            )
                                        }
                                    )
                                } else {
                                    ScheduleList(
                                        currentDateTime = currentDateTime,
                                        studyDay = studyDay,
                                        spacer = {
                                            Spacer(modifier = Modifier.height(128.dp))
                                            Spacer(
                                                modifier = Modifier.windowInsetsBottomHeight(
                                                    WindowInsets.navigationBars
                                                )
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                )
            }

            SIExtensibleVisibility(scheduleUiState.isSearchContentVisible) {
                SearchContent(
                    paddingValues = it,
                    isFoundedListsVisible = scheduleUiState.isFoundedListsVisible,
                    searchedListsTips = searchedListsTips,
                    onSearchButtonClick = { onSearchButtonClick() }
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun SchedulePageLoadingPreview() {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleUiState(
                isUserBinded = true,
                userDescription = "ИСТб-20-3"
            ),
            isLoading = true,
            studyDay = null,
            weeksList = listOf(
                Week(LocalDate.of(2023, 5, 29))
            ),
            currentDateTime = LocalDateTime.of(2023, 6, 1, 12, 30),
            selectedDate = LocalDate.of(2023, 6, 3),
            searchedListsTips = SearchedLists(),
            onSearchButtonClick = { },
            onDateSelect = { },
            onValueInputDone = { },
            onSetupScheduleClick = { }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageUnknownUserPreview() {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleUiState(),
            isLoading = false,
            studyDay = null,
            weeksList = listOf(
                Week(LocalDate.of(2023, 5, 29))
            ),
            currentDateTime = LocalDateTime.of(2023, 6, 1, 12, 30),
            selectedDate = LocalDate.of(2023, 6, 3),
            searchedListsTips = SearchedLists(),
            onSearchButtonClick = { },
            onDateSelect = { },
            onValueInputDone = { },
            onSetupScheduleClick = { }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageWeekendPreview() {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleUiState(
                isUserBinded = true,
                userDescription = "ИСТб-20-3"
            ),
            isLoading = false,
            studyDay = StudyDay(
                date = "",
                lessons = emptyList()
            ),
            weeksList = listOf(
                Week(LocalDate.of(2023, 5, 29))
            ),
            currentDateTime = LocalDateTime.of(2023, 6, 1, 12, 30),
            selectedDate = LocalDate.of(2023, 6, 3),
            searchedListsTips = SearchedLists(),
            onSearchButtonClick = { },
            onDateSelect = { },
            onValueInputDone = { },
            onSetupScheduleClick = { }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageScheduleList(
    @PreviewParameter(SampleStudyDayProvider::class) studyDay: StudyDay
) {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleUiState(
                isUserBinded = true,
                userDescription = "ИСТб-20-3"
            ),
            isLoading = false,
            studyDay = studyDay,
            weeksList = listOf(
                Week(LocalDate.of(2023, 5, 29))
            ),
            currentDateTime = LocalDateTime.of(2023, 6, 1, 12, 30),
            selectedDate = LocalDate.of(2023, 6, 3),
            searchedListsTips = SearchedLists(),
            onSearchButtonClick = { },
            onDateSelect = { },
            onValueInputDone = { },
            onSetupScheduleClick = { }
        )
    }
}
