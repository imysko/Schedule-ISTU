package com.istu.schedule.domain.usecase.schedule

import com.istu.schedule.domain.entities.schedule.ScheduleType
import com.istu.schedule.domain.entities.schedule.StudyDay
import com.istu.schedule.domain.repository.schedule.ScheduleRepository
import javax.inject.Inject

interface GetScheduleOnDayUseCase {

    suspend operator fun invoke(
        scheduleType: ScheduleType,
        id: Int,
        dateString: String,
    ) : Result<StudyDay>
}

class GetScheduleOnDayUseCaseImpl @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) : GetScheduleOnDayUseCase {

    override suspend fun invoke(
        scheduleType: ScheduleType,
        id: Int,
        dateString: String,
    ): Result<StudyDay> {
        return when (scheduleType) {
            ScheduleType.BY_GROUP -> scheduleRepository.getGroupScheduleOnDay(
                groupId = id,
                dateString = dateString,
            )

            ScheduleType.BY_TEACHER -> scheduleRepository.getTeacherScheduleOnDay(
                teacherId = id,
                dateString = dateString,
            )

            ScheduleType.BY_CLASSROOM -> scheduleRepository.getClassroomScheduleOnDay(
                classroomId = id,
                dateString = dateString,
            )
        }
    }
}
