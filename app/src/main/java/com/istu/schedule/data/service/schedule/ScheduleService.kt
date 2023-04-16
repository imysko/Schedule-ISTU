package com.istu.schedule.data.service.schedule

import com.istu.schedule.domain.model.schedule.StudyDay
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleService {

    @GET("schedule-api/schedules/group-schedule/day")
    suspend fun getGroupScheduleOnDay(
        @Query("groupId") groupId: Int,
        @Query("dateString") dateString: String,
    ): Response<List<StudyDay>>

    @GET("schedule-api/schedules/group-schedule/weekly")
    suspend fun getGroupScheduleOnWeek(
        @Query("groupId") groupId: Int,
        @Query("startDateWeekString") startDateWeekString: String,
    ): Response<List<StudyDay>>

    @GET("schedule-api/schedules/group-schedule/month")
    suspend fun getGroupScheduleOnMonth(
        @Query("groupId") groupId: Int,
        @Query("month") month: Int,
    ): Response<List<StudyDay>>

    @GET("schedule-api/schedules/teacher-schedule/day")
    suspend fun getTeacherScheduleOnDay(
        @Query("teacherId") teacherId: Int,
        @Query("dateString") dateString: String,
    ): Response<List<StudyDay>>

    @GET("schedule-api/schedules/teacher-schedule/weekly")
    suspend fun getTeacherScheduleOnWeek(
        @Query("teacherId") teacherId: Int,
        @Query("startDateWeekString") startDateWeekString: String,
    ): Response<List<StudyDay>>

    @GET("schedule-api/schedules/teacher-schedule/month")
    suspend fun getTeacherScheduleOnMonth(
        @Query("teacherId") teacherId: Int,
        @Query("month") month: Int,
    ): Response<List<StudyDay>>
}
