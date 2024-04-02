package com.istu.schedule.data.api.service.schedule

import com.istu.schedule.data.api.entities.schedule.StudyDayResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleService {

    @GET("schedule-api/schedules/group-schedule/day")
    suspend fun getGroupScheduleOnDay(
        @Query("groupId") groupId: Int,
        @Query("dateString") dateString: String
    ): Response<List<StudyDayResponse>>

    @GET("schedule-api/schedules/group-schedule/weekly")
    suspend fun getGroupScheduleOnWeek(
        @Query("groupId") groupId: Int,
        @Query("startDateWeekString") startDateWeekString: String
    ): Response<List<StudyDayResponse>>

    @GET("schedule-api/schedules/group-schedule/month")
    suspend fun getGroupScheduleOnMonth(
        @Query("groupId") groupId: Int,
        @Query("month") month: Int
    ): Response<List<StudyDayResponse>>

    @GET("schedule-api/schedules/teacher-schedule/day")
    suspend fun getTeacherScheduleOnDay(
        @Query("teacherId") teacherId: Int,
        @Query("dateString") dateString: String
    ): Response<List<StudyDayResponse>>

    @GET("schedule-api/schedules/teacher-schedule/weekly")
    suspend fun getTeacherScheduleOnWeek(
        @Query("teacherId") teacherId: Int,
        @Query("startDateWeekString") startDateWeekString: String
    ): Response<List<StudyDayResponse>>

    @GET("schedule-api/schedules/teacher-schedule/month")
    suspend fun getTeacherScheduleOnMonth(
        @Query("teacherId") teacherId: Int,
        @Query("month") month: Int
    ): Response<List<StudyDayResponse>>

    @GET("schedule-api/schedules/classroom-schedule/day")
    suspend fun getClassroomScheduleOnDay(
        @Query("classroomId") classroomId: Int,
        @Query("dateString") dateString: String
    ): Response<List<StudyDayResponse>>

    @GET("schedule-api/schedules/classroom-schedule/weekly")
    suspend fun getClassroomScheduleOnWeek(
        @Query("classroomId") classroomId: Int,
        @Query("startDateWeekString") startDateWeekString: String
    ): Response<List<StudyDayResponse>>

    @GET("schedule-api/schedules/classroom-schedule/month")
    suspend fun getClassroomScheduleOnMonth(
        @Query("classroomId") classroomId: Int,
        @Query("month") month: Int
    ): Response<List<StudyDayResponse>>
}
