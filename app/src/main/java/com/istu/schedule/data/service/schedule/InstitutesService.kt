package com.istu.schedule.data.service.schedule

import com.istu.schedule.domain.model.schedule.Institute
import retrofit2.Response
import retrofit2.http.GET

interface InstitutesService {
    @GET("schedule-api/institutes")
    suspend fun getInstitutes(): Response<List<Institute>>
}
