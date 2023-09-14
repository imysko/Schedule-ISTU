package com.istu.schedule.data.repository.schedule

import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.service.schedule.ScheduleService
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.domain.repository.schedule.ScheduleRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleService: ScheduleService
) : ScheduleRepository {

    private val cachedList: MutableList<StudyDay> = mutableListOf()

    override suspend fun getGroupScheduleOnDay(
        groupId: Int,
        dateString: String
    ): Result<List<StudyDay>> {
        val apiResponse = scheduleService.getGroupScheduleOnDay(groupId, dateString).body()

        if (apiResponse != null) {
            cachedList.clear()

            cachedList.addAll(apiResponse)
            return Result.success(cachedList)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
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
    ): Result<List<StudyDay>> {
        val apiResponse = scheduleService.getTeacherScheduleOnDay(teacherId, dateString).body()

        if (apiResponse != null) {
            cachedList.clear()

            cachedList.addAll(apiResponse)
            return Result.success(cachedList)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
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
    ): Result<List<StudyDay>> {
        val apiResponse = scheduleService.getClassroomScheduleOnDay(classroomId, dateString).body()

        if (apiResponse != null) {
            cachedList.clear()

            cachedList.addAll(apiResponse)
            return Result.success(cachedList)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
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
