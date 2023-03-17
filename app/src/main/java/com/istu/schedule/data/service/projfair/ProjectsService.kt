package com.istu.schedule.data.service.projfair

import com.istu.schedule.data.model.response.ProjectsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProjectsService {
    @GET("api/projects/filter")
    suspend fun getProjects(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 10
    ): Response<ProjectsResponse>
}