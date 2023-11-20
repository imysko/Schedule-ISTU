package com.istu.schedule.data.api.service.schedule

import com.istu.schedule.data.api.entities.schedule.InstituteResponse
import retrofit2.Response
import retrofit2.http.GET

interface InstitutesService {
    @GET("schedule-api/institutes")
    suspend fun getInstitutes(): Response<List<InstituteResponse>>
}
