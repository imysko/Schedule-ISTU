package com.istu.schedule.data.api.service.projfair

import com.istu.schedule.domain.entities.projfair.ProjectState
import retrofit2.Response
import retrofit2.http.GET

interface ProjectStateService {
    @GET("api/states")
    suspend fun getProjectStatesList(): Response<List<ProjectState>>
}
