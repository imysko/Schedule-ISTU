package me.progneo.projfair.data.service

import me.progneo.projfair.domain.model.FiltersDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

internal interface FiltersDataService {
    @GET("api/skills")
    suspend fun getSkills(
        @Header("Cookie") token: String = ""
    ): Response<FiltersDataResponse>
}
