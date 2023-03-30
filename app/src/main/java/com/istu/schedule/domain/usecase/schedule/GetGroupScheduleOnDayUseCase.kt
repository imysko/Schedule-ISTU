package com.istu.schedule.domain.usecase.schedule

import com.istu.schedule.domain.model.schedule.Schedule
import com.istu.schedule.domain.repository.schedule.ScheduleRepository
import javax.inject.Inject

class GetGroupScheduleOnDayUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {

    suspend fun getGroupScheduleOnDay(groupId: Int, dateString: String): Result<List<Schedule>> {
        return scheduleRepository.getGroupScheduleOnDay(groupId, dateString)
    }
}