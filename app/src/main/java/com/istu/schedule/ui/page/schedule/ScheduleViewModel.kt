package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.istu.schedule.domain.entities.Week
import com.istu.schedule.domain.entities.schedule.ScheduleType
import com.istu.schedule.domain.entities.schedule.StudyDay
import com.istu.schedule.ui.components.base.BaseViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

abstract class ScheduleViewModel : BaseViewModel() {

    var schedule by mutableStateOf<StudyDay?>(null)
        protected set

    private val _calendarState = MutableStateFlow(LazyListState())
    val calendarState: StateFlow<LazyListState> = _calendarState.asStateFlow()

    val currentDate = flow {
        while (true) {
            delay(1.minutes)
            emit(LocalDate.now())
        }
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            LocalDate.now()
        )

    val currentDateTime = flow {
        while (true) {
            delay(1.seconds)
            emit(LocalDateTime.now())
        }
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            LocalDateTime.now()
        )

    private val _weeksList = MutableLiveData<MutableList<Week>>().apply {
        value = mutableListOf()

        val startDate = currentDateTime.value.toLocalDate()
            .minusDays((currentDateTime.value.toLocalDate().dayOfWeek.value - 1).toLong())

        value?.let {
            it.add(Week(startDate.minusWeeks(1)))
            it.add(Week(startDate))
            it.add(Week(startDate.plusWeeks(1)))
        }
    }
    val weeksList: LiveData<MutableList<Week>> = _weeksList

    private val _selectedDate = MutableLiveData<LocalDate>().apply {
        value = currentDateTime.value.toLocalDate()
    }
    val selectedDate: LiveData<LocalDate> = _selectedDate

    fun addWeekForward() {
        _weeksList.value?.let {
            it.add(
                0,
                Week(startDayOfWeek = it.first().startDayOfWeek.minusWeeks(1))
            )
        }
    }

    fun addWeekBackward() {
        _weeksList.value?.let {
            it.add(
                Week(
                    startDayOfWeek = it.last().startDayOfWeek.plusWeeks(1)
                )
            )
        }
    }

    open fun selectDate(selectedDate: LocalDate) {
        _selectedDate.value = selectedDate
    }

    protected abstract fun getSchedule(scheduleType: ScheduleType, id: Int)
}

@Deprecated("")
data class ScheduleOldUiState(
    val isShowSchedule: Boolean = true,
    val calendarState: LazyListState = LazyListState(),
    val description: String? = null,
    val isShowDescription: Boolean = false
)
