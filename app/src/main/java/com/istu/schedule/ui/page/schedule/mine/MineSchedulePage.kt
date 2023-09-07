package com.istu.schedule.ui.page.schedule.mine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.istu.schedule.ui.page.schedule.SchedulePage
import com.istu.schedule.util.NavDestinations
import com.istu.schedule.util.collectAsStateValue
import java.time.LocalDate

@Composable
fun MineSchedulePage(
    navController: NavHostController,
    viewModel: MineScheduleViewModel = hiltViewModel()
) {
    val isLoading by viewModel.loading.observeAsState(initial = false)

    val scheduleUiState = viewModel.scheduleUiState.collectAsStateValue()

    val weeksList by viewModel.weeksList.observeAsState(initial = emptyList())
    val currentDateTime by viewModel.currentDateTime.collectAsStateWithLifecycle()
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
        val currentWeek = weeksList.first { it.days.contains(LocalDate.now()) }
        scheduleUiState.calendarState.scrollToItem(weeksList.indexOf(currentWeek))
    }

    LaunchedEffect(navController) {
        viewModel.updateUserInformation()
    }

    LaunchedEffect(selectedDate) {
        viewModel.getMineSchedule()
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
        onSearchButtonClick = { navController.navigate(NavDestinations.SEARCH_SCHEDULE) },
        onDateSelect = { viewModel.selectDate(it) },
        onSetupScheduleClick = { navController.navigate(NavDestinations.BINDING) },
        onBackClick = { }
    )
}
