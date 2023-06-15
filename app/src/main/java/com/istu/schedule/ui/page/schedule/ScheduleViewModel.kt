package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.enums.UserStatus
import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.model.User
import com.istu.schedule.data.model.Week
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.domain.usecase.schedule.GetGroupScheduleOnDayUseCase
import com.istu.schedule.domain.usecase.schedule.GetTeacherScheduleOnDayUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val _useCaseGroupScheduleOnDay: GetGroupScheduleOnDayUseCase,
    private val _useCaseTeacherScheduleOnDay: GetTeacherScheduleOnDayUseCase,
    private val _user: User,
) : BaseViewModel() {

    private val _scheduleUiState = MutableStateFlow(ScheduleUiState())
    val scheduleUiState: StateFlow<ScheduleUiState> = _scheduleUiState.asStateFlow()

    private val _schedule = MutableLiveData<StudyDay>()
    val schedule: LiveData<StudyDay> = _schedule

    private val _currentDate = MutableLiveData<LocalDate>().apply {
        value = LocalDate.now()
    }
    val currentDate: LiveData<LocalDate> = _currentDate

    private val _weeksList = MutableLiveData<MutableList<Week>>().apply {
        value = mutableListOf()

        val startDate = _currentDate.value!!
            .minusDays((_currentDate.value!!.dayOfWeek.value - 1).toLong())

        value!!.add(Week(startDate))
    }
    val weeksList: LiveData<MutableList<Week>> = _weeksList

    private val _selectedDate = MutableLiveData<LocalDate>().apply {
        value = _currentDate.value
    }
    val selectedDate: LiveData<LocalDate> = _selectedDate

    init {
        updateUserInformation()
        addWeekForward()
        addWeekBackward()
    }

    fun updateUserInformation() {
        if (_user.userType != UserStatus.UNKNOWN) {
            _scheduleUiState.update {
                it.copy(
                    isUserBinded = true,
                    userDescription = _user.userDescription,
                )
            }
        }
    }

    fun addWeekForward() {
        _weeksList.value!!.add(
            0,
            Week(
                startDayOfWeek = _weeksList.value!!.first().startDayOfWeek.minusWeeks(1),
            ),
        )
    }

    fun addWeekBackward() {
        _weeksList.value!!.add(
            Week(
                startDayOfWeek = _weeksList.value!!.last().startDayOfWeek.plusWeeks(1),
            ),
        )
    }

    fun selectDate(selectedDate: LocalDate) {
        _selectedDate.value = selectedDate
    }

    fun getSchedule() {
        when (_user.userType) {
            UserStatus.STUDENT -> {
                call({
                    _useCaseGroupScheduleOnDay.getGroupScheduleOnDay(
                        groupId = _user.userId!!,
                        dateString = _selectedDate.value.toString(),
                    )
                }, onSuccess = { list ->
                    _schedule.postValue(list.first { it.date == _selectedDate.value.toString() })
                }, onError = {it as RequestException
                    _schedule.postValue(
                        StudyDay(
                            date = _selectedDate.value.toString(),
                            lessons = emptyList(),
                        )
                    )
                })
            }
            UserStatus.TEACHER -> {
                call({
                    _useCaseTeacherScheduleOnDay.getTeacherScheduleOnDay(
                        teacherId = _user.userId!!,
                        dateString = _selectedDate.value.toString(),
                    )
                }, onSuccess = { list ->
                    _schedule.postValue(list.first { it.date == _selectedDate.value.toString() })
                }, onError = {it as RequestException
                    _schedule.postValue(
                        StudyDay(
                            date = _selectedDate.value.toString(),
                            lessons = emptyList(),
                        )
                    )
                })
            }
            else -> { }
        }
    }
}

data class ScheduleUiState(
    val isUserBinded: Boolean = false,
    val userDescription: String? = null,
    val calendarState: LazyListState = LazyListState(),
)
