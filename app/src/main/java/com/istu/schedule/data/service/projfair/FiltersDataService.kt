package com.istu.schedule.data.service.projfair

import com.istu.schedule.data.model.response.FiltersDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface FiltersDataService {
    @GET("api/skills")
    suspend fun getSkills(
        @Header("Cookie") token: String = "",
    ): Response<FiltersDataResponse>
}
