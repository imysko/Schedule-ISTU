package me.progneo.projfair.data.api.service

import me.progneo.projfair.domain.model.FiltersDataResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface FiltersDataService {
    @GET("api/skills")
    suspend fun getSkills(): Response<FiltersDataResponse>
}
