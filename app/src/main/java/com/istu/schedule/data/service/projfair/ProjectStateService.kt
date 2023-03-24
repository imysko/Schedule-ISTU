package com.istu.schedule.data.service.projfair

import com.istu.schedule.domain.model.projfair.ProjectState
import retrofit2.Response
import retrofit2.http.GET

interface ProjectStateService {
    @GET("api/states")
    suspend fun getProjectStatesList(): Response<List<ProjectState>>
}