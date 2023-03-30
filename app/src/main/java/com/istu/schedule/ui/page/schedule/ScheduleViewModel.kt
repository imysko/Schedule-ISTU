package com.istu.schedule.ui.page.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.enums.UserStatus
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.model.schedule.Schedule
import com.istu.schedule.domain.usecase.schedule.GetGroupScheduleOnDayUseCase
import com.istu.schedule.domain.usecase.schedule.GetTeacherScheduleOnDayUseCase
import com.istu.schedule.ui.components.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val _useCaseGroupScheduleOnDay: GetGroupScheduleOnDayUseCase,
    private val _useCaseTeacherScheduleOnDay: GetTeacherScheduleOnDayUseCase,
    private val _user: User
) : BaseViewModel() {

    private val _scheduleList = MutableLiveData<List<Schedule>>()
    val scheduleList: LiveData<List<Schedule>> = _scheduleList

    init {
        getSchedule()
    }

    private fun getSchedule() {
        when (_user.userType) {
            UserStatus.STUDENT -> {
                call({
                    _useCaseGroupScheduleOnDay.getGroupScheduleOnDay(
                        groupId = _user.userId!!,
                        dateString = LocalDate.now().toString())
                }, onSuccess = {
                    _scheduleList.postValue(it)
                })
            }
            UserStatus.TEACHER -> {
                call({
                    _useCaseTeacherScheduleOnDay.getTeacherScheduleOnDay(
                        teacherId = _user.userId!!,
                        dateString = LocalDate.now().toString())
                }, onSuccess = {
                    _scheduleList.postValue(it)
                })
            }
            else -> { }
        }
    }
}