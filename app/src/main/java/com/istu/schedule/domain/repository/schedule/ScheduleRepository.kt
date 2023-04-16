package com.istu.schedule.domain.repository.schedule

import com.istu.schedule.domain.model.schedule.StudyDay

interface ScheduleRepository {
    suspend fun getGroupScheduleOnDay(
        groupId: Int,
        dateString: String,
    ): Result<List<StudyDay>>

    suspend fun getGroupScheduleOnWeek(
        groupId: Int,
        startDateWeekString: String,
    ): Result<List<StudyDay>>

    suspend fun getGroupScheduleOnMonth(
        groupId: Int,
        month: Int,
    ): Result<List<StudyDay>>

    suspend fun getTeacherScheduleOnDay(
        teacherId: Int,
        dateString: String,
    ): Result<List<StudyDay>>

    suspend fun getTeacherScheduleOnWeek(
        teacherId: Int,
        startDateWeekString: String,
    ): Result<List<StudyDay>>

    suspend fun getTeacherScheduleOnMonth(
        teacherId: Int,
        month: Int,
    ): Result<List<StudyDay>>
}
