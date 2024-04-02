package com.istu.schedule.ui.page.schedule.mine

import android.util.Log
import com.istu.schedule.data.api.entities.RequestException
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.entities.Subgroup
import com.istu.schedule.domain.entities.UserStatus
import com.istu.schedule.domain.entities.schedule.Lesson
import com.istu.schedule.domain.entities.schedule.ScheduleType
import com.istu.schedule.domain.entities.schedule.StudyDay
import com.istu.schedule.domain.usecase.schedule.GetScheduleOnDayUseCase
import com.istu.schedule.ui.page.schedule.ScheduleViewModel
import com.istu.schedule.ui.util.VibrationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.internal.http.HTTP_NOT_FOUND

@HiltViewModel
class MineScheduleViewModel @Inject constructor(
    val vibrationManager: VibrationManager,
    private val useCaseScheduleOnDay: GetScheduleOnDayUseCase,
    private val _user: User
) : ScheduleViewModel() {

    private val _uiState = MutableStateFlow<MineScheduleUiState>(MineScheduleUiState.UnknownUser)
    val uiState: StateFlow<MineScheduleUiState>
        get() = _uiState.asStateFlow()

    init {
        updateUserInformation()
        getMineSchedule()
    }

    fun updateUserInformation() {
        if (_user.userType != UserStatus.UNKNOWN) {
            _uiState.tryEmit(MineScheduleUiState.Schedule(description = _user.userDescription))
        } else {
            _uiState.tryEmit(MineScheduleUiState.UnknownUser)
        }
    }

    override fun selectDate(selectedDate: LocalDate) {
        super.selectDate(selectedDate)
        getMineSchedule()
    }

    private fun getMineSchedule() {
        _user.userId?.let { userId ->
            when (_user.userType) {
                UserStatus.STUDENT -> {
                    getSchedule(ScheduleType.BY_GROUP, userId)
                }
                UserStatus.TEACHER -> {
                    getSchedule(ScheduleType.BY_TEACHER, userId)
                }
                else -> Unit
            }
        }
    }

    override fun getSchedule(scheduleType: ScheduleType, id: Int) {
        _uiState.tryEmit(MineScheduleUiState.OnLoading(_user.userDescription))

        call({
            useCaseScheduleOnDay(
                scheduleType = scheduleType,
                id = id,
                dateString = selectedDate.value.toString()
            )
        }, onSuccess = { studyDay ->
            try {
                val received = when (_user.userSubgroup) {
                    Subgroup.FIRST, Subgroup.SECOND -> filterSchedule(studyDay, _user.userSubgroup)
                    Subgroup.ALL -> studyDay
                }

                if (received.lessons.isEmpty()) {
                    _uiState.tryEmit(MineScheduleUiState.Weekend(_user.userDescription))
                } else {
                    _uiState.tryEmit(MineScheduleUiState.Schedule(_user.userDescription))
                }

                schedule = received
            } catch (ex: Exception) {
                Log.e("getSchedule", ex.toString())

                _uiState.tryEmit(
                    MineScheduleUiState.Error(
                        description = _user.userDescription,
                        message = ex.message ?: ""
                    )
                )
            }
        }, onError = {
            it as RequestException
            if (it.code == HTTP_NOT_FOUND) {
                _uiState.tryEmit(MineScheduleUiState.Weekend(_user.userDescription))
            } else {
                _uiState.tryEmit(
                    MineScheduleUiState.Error(
                        description = _user.userDescription,
                        message = it.message ?: ""
                    )
                )
            }
        }, onNetworkUnavailable = {
            _uiState.tryEmit(MineScheduleUiState.NoInternetConnection(_user.userDescription))
        })
    }

    private fun filterSchedule(studyDay: StudyDay, subgroup: Subgroup): StudyDay {
        return StudyDay(
            date = studyDay.date,
            lessons = studyDay.lessons.map { lesson ->
                Lesson(
                    time = lesson.time,
                    breakTimeAfter = lesson.breakTimeAfter,
                    schedules = lesson.schedules.filter {
                        it.subgroup == subgroup.subgroupId || it.subgroup == Subgroup.ALL.subgroupId
                    }
                )
            }.filter {
                it.schedules.any()
            }
        )
    }
}
