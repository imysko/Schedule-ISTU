package com.istu.schedule.data.api.service.schedule

import com.istu.schedule.data.api.entities.schedule.ClassroomResponse
import retrofit2.Response
import retrofit2.http.GET

interface ClassroomsService {
    @GET("schedule-api/classrooms")
    suspend fun getClassrooms(): Response<List<ClassroomResponse>>
}
