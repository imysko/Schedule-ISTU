package com.istu.schedule.domain.usecase.schedule

import com.istu.schedule.data.enums.ScheduleType
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.domain.repository.schedule.ScheduleRepository
import javax.inject.Inject

class GetScheduleOnDayUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {

    suspend fun getScheduleOnDay(
        scheduleType: ScheduleType,
        id: Int,
        dateString: String
    ): Result<List<StudyDay>> {
        return when (scheduleType) {
            ScheduleType.BY_GROUP -> scheduleRepository.getGroupScheduleOnDay(groupId = id, dateString)
            ScheduleType.BY_TEACHER -> scheduleRepository.getTeacherScheduleOnDay(teacherId = id, dateString)
            ScheduleType.BY_CLASSROOM -> scheduleRepository.getClassroomScheduleOnDay(classroomId = id, dateString)
        }
    }
}
