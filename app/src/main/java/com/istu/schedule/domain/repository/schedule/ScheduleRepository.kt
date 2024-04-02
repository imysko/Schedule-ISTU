package com.istu.schedule.domain.repository.schedule

import com.istu.schedule.domain.entities.schedule.StudyDay

interface ScheduleRepository {

    suspend fun getGroupScheduleOnDay(
        groupId: Int,
        dateString: String
    ): Result<StudyDay>

    suspend fun getGroupScheduleOnWeek(
        groupId: Int,
        startDateWeekString: String
    ): Result<List<StudyDay>>

    suspend fun getGroupScheduleOnMonth(
        groupId: Int,
        month: Int
    ): Result<List<StudyDay>>

    suspend fun getTeacherScheduleOnDay(
        teacherId: Int,
        dateString: String
    ): Result<StudyDay>

    suspend fun getTeacherScheduleOnWeek(
        teacherId: Int,
        startDateWeekString: String
    ): Result<List<StudyDay>>

    suspend fun getTeacherScheduleOnMonth(
        teacherId: Int,
        month: Int
    ): Result<List<StudyDay>>

    suspend fun getClassroomScheduleOnDay(
        classroomId: Int,
        dateString: String
    ): Result<StudyDay>

    suspend fun getClassroomScheduleOnWeek(
        classroomId: Int,
        startDateWeekString: String
    ): Result<List<StudyDay>>

    suspend fun getClassroomScheduleOnMonth(
        classroomId: Int,
        month: Int
    ): Result<List<StudyDay>>
}
