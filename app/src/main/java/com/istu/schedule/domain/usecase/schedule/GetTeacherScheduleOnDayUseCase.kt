package com.istu.schedule.domain.usecase.schedule

import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.domain.repository.schedule.ScheduleRepository
import javax.inject.Inject

class GetTeacherScheduleOnDayUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {

    suspend fun getTeacherScheduleOnDay(teacherId: Int, dateString: String): Result<List<StudyDay>> {
        return scheduleRepository.getTeacherScheduleOnDay(teacherId, dateString)
    }
}
