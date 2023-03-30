package com.istu.schedule.data.service.schedule

import com.istu.schedule.domain.model.schedule.Teacher
import retrofit2.Response
import retrofit2.http.GET

interface TeachersService {
    @GET("schedule-api/teachers")
    suspend fun getTeachers(
    ) : Response<List<Teacher>>
}