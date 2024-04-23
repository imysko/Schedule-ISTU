package me.progneo.projfair.data.api.service

import me.progneo.projfair.domain.model.ProjectState
import retrofit2.Response
import retrofit2.http.GET

internal interface ProjectStateService {
    @GET("api/states")
    suspend fun getProjectStateList(): Response<List<ProjectState>>
}
