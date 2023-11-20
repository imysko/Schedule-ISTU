package com.istu.schedule.data.api.service.schedule

import com.istu.schedule.data.api.entities.schedule.TeacherResponse
import retrofit2.Response
import retrofit2.http.GET

interface TeachersService {
    @GET("schedule-api/teachers")
    suspend fun getTeachers(): Response<List<TeacherResponse>>
}
