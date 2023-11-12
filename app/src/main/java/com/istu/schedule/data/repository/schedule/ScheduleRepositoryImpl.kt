package com.istu.schedule.data.repository.schedule

import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.service.schedule.ScheduleService
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.domain.repository.schedule.ScheduleRepository
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleService: ScheduleService
) : ScheduleRepository {

    override suspend fun getGroupScheduleOnDay(
        groupId: Int,
        dateString: String
    ): Result<StudyDay> {
        val apiResponse = scheduleService.getGroupScheduleOnDay(groupId, dateString)

        apiResponse.body()?.let { result ->
            return Result.success(result.first { it.date == dateString })
        }

        return Result.failure(
            RequestException(
                code = apiResponse.code(),
                message = apiResponse.message(),
            )
        )
    }

    override suspend fun getGroupScheduleOnWeek(
        groupId: Int,
        startDateWeekString: String
    ): Result<List<StudyDay>> {
        TODO("Not yet implemented")
    }

    override suspend fun getGroupScheduleOnMonth(groupId: Int, month: Int): Result<List<StudyDay>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTeacherScheduleOnDay(
        teacherId: Int,
        dateString: String
    ): Result<StudyDay> {
        val apiResponse = scheduleService.getTeacherScheduleOnDay(teacherId, dateString)

        apiResponse.body()?.let { result ->
            return Result.success(result.first { it.date == dateString })
        }

        return Result.failure(
            RequestException(
                code = apiResponse.code(),
                message = apiResponse.message(),
            )
        )
    }

    override suspend fun getTeacherScheduleOnWeek(
        teacherId: Int,
        startDateWeekString: String
    ): Result<List<StudyDay>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTeacherScheduleOnMonth(
        teacherId: Int,
        month: Int
    ): Result<List<StudyDay>> {
        TODO("Not yet implemented")
    }

    override suspend fun getClassroomScheduleOnDay(
        classroomId: Int,
        dateString: String
    ): Result<StudyDay> {
        val apiResponse = scheduleService.getClassroomScheduleOnDay(classroomId, dateString)

        apiResponse.body()?.let { result ->
            return Result.success(result.first { it.date == dateString })
        }

        return Result.failure(
            RequestException(
                code = apiResponse.code(),
                message = apiResponse.message(),
            )
        )
    }

    override suspend fun getClassroomScheduleOnWeek(
        classroomId: Int,
        startDateWeekString: String
    ): Result<List<StudyDay>> {
        TODO("Not yet implemented")
    }

    override suspend fun getClassroomScheduleOnMonth(
        classroomId: Int,
        month: Int
    ): Result<List<StudyDay>> {
        TODO("Not yet implemented")
    }
}
