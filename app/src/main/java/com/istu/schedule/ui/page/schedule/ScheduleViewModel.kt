package com.istu.schedule.ui.page.schedule

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.istu.schedule.data.enums.ScheduleType
import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.model.Week
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.domain.usecase.schedule.GetScheduleOnDayUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
open class ScheduleViewModel @Inject constructor(
    protected val _useCaseScheduleOnDay: GetScheduleOnDayUseCase
) : BaseViewModel() {

    protected val _scheduleUiState = MutableStateFlow(ScheduleUiState())
    val scheduleUiState: StateFlow<ScheduleUiState> = _scheduleUiState.asStateFlow()

    protected val _schedule = MutableLiveData<StudyDay>()
    val schedule: LiveData<StudyDay> = _schedule

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

        value!!.add(Week(startDate.minusWeeks(1)))
        value!!.add(Week(startDate))
        value!!.add(Week(startDate.plusWeeks(1)))
    }
    val weeksList: LiveData<MutableList<Week>> = _weeksList

    protected val _selectedDate = MutableLiveData<LocalDate>().apply {
        value = currentDateTime.value.toLocalDate()
    }
    val selectedDate: LiveData<LocalDate> = _selectedDate

    fun addWeekForward() {
        _weeksList.value!!.add(
            0,
            Week(
                startDayOfWeek = _weeksList.value!!.first().startDayOfWeek.minusWeeks(1)
            )
        )
    }

    fun addWeekBackward() {
        _weeksList.value!!.add(
            Week(
                startDayOfWeek = _weeksList.value!!.last().startDayOfWeek.plusWeeks(1)
            )
        )
    }

    fun selectDate(selectedDate: LocalDate) {
        _selectedDate.value = selectedDate
    }

    open fun getSchedule(scheduleType: ScheduleType, id: Int) {
        call({
            _useCaseScheduleOnDay.getScheduleOnDay(
                scheduleType = scheduleType,
                id = id,
                dateString = _selectedDate.value.toString()
            )
        }, onSuccess = { list ->
            try {
                _schedule.postValue(list.first { it.date == _selectedDate.value.toString() })
            } catch (ex: Exception) {
                Log.e("getSchedule", ex.toString())
            }
        }, onError = {
            it as RequestException
            _schedule.postValue(
                StudyDay(
                    date = _selectedDate.value.toString(),
                    lessons = emptyList()
                )
            )
        })
    }
}

data class ScheduleUiState(
    val isShowSchedule: Boolean = true,
    val calendarState: LazyListState = LazyListState(),
    val description: String? = null,
    val isShowDescription: Boolean = false
)
