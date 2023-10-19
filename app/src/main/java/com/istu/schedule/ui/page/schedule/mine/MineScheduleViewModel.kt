package com.istu.schedule.ui.page.schedule.mine

import android.util.Log
import com.istu.schedule.data.enums.ScheduleType
import com.istu.schedule.data.enums.Subgroup
import com.istu.schedule.data.enums.UserStatus
import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.model.schedule.Lesson
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.domain.usecase.schedule.GetScheduleOnDayUseCase
import com.istu.schedule.ui.page.schedule.ScheduleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MineScheduleViewModel @Inject constructor(
    useCaseScheduleOnDay: GetScheduleOnDayUseCase,
    private val _user: User
) : ScheduleViewModel(useCaseScheduleOnDay) {

    init {
        updateUserInformation()
    }

    fun updateUserInformation() {
        if (_user.userType != UserStatus.UNKNOWN) {
            _scheduleUiState.update {
                it.copy(
                    isShowSchedule = true,
                    isShowDescription = true,
                    description = _user.userDescription
                )
            }
        } else {
            _scheduleUiState.update {
                it.copy(isShowSchedule = false)
            }
        }
    }

    fun getMineSchedule() {
        when (_user.userType) {
            UserStatus.STUDENT -> {
                getSchedule(ScheduleType.BY_GROUP, _user.userId!!)
            }
            UserStatus.TEACHER -> {
                getSchedule(ScheduleType.BY_TEACHER, _user.userId!!)
            }
            else -> Unit
        }
    }

    override fun getSchedule(scheduleType: ScheduleType, id: Int) {
        call({
            _useCaseScheduleOnDay.getScheduleOnDay(
                scheduleType = scheduleType,
                id = id,
                dateString = _selectedDate.value.toString()
            )
        }, onSuccess = { list ->
            try {
                val received = list.first { it.date == _selectedDate.value.toString() }

                when (_user.userSubgroup) {
                    Subgroup.FIRST, Subgroup.SECOND -> {
                        _schedule.postValue(filterSchedule(received, _user.userSubgroup))
                    }
                    Subgroup.ALL -> _schedule.postValue(received)
                }
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

    private fun filterSchedule(studyDay: StudyDay, subgroup: Subgroup): StudyDay {
        return StudyDay(
            date = studyDay.date,
            lessons = studyDay.lessons.map { lesson ->
                Lesson(
                    time = lesson.time,
                    breakTimeAfter = lesson.breakTimeAfter,
                    schedules = lesson.schedules.filter {
                        it.subgroup == subgroup.subgroupId || it.subgroup == Subgroup.ALL.subgroupId
                    },
                )
            }.filter {
                it.schedules.any()
            },
        )
    }
}
