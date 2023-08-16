package com.istu.schedule.ui.page.schedule.mine

import com.istu.schedule.data.enums.ScheduleType
import com.istu.schedule.data.enums.UserStatus
import com.istu.schedule.data.model.User
import com.istu.schedule.domain.usecase.schedule.GetScheduleOnDayUseCase
import com.istu.schedule.ui.page.schedule.ScheduleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MineScheduleViewModel @Inject constructor(
    _useCaseScheduleOnDay: GetScheduleOnDayUseCase,
    private val _user: User
) : ScheduleViewModel(_useCaseScheduleOnDay) {

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
            else -> {}
        }
    }
}
