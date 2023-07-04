package com.istu.schedule.data.service.schedule

import com.istu.schedule.domain.model.schedule.Classroom
import retrofit2.Response
import retrofit2.http.GET

interface ClassroomsService {
    @GET("schedule-api/classrooms")
    suspend fun getClassrooms(): Response<List<Classroom>>
}
