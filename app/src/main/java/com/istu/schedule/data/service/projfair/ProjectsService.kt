package com.istu.schedule.data.service.projfair

import com.istu.schedule.data.model.response.ProjectsResponse
import com.istu.schedule.domain.model.projfair.Project
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ProjectsService {
    @GET("api/projects/filter")
    suspend fun getProjects(
        @Header("Cookie") token: String = "",
        @Query("title") title: String = "",
        @Query("page") page: Int = 0,
        @Query("difficulty[]") difficulties: List<Int> = emptyList(),
        @Query("state[]") states: List<Int> = emptyList(),
        @Query("specialties[]") specialties: List<Int> = emptyList(),
        @Query("skills[]") skills: List<Int> = emptyList(),
        @Query("order") order: String = "asc",
        @Query("sortBy") sortBy: String = "state",
    ): Response<ProjectsResponse>

    @GET("api/projects/{id}")
    suspend fun getProject(
        @Path("id") id: Int = 0,
    ): Response<Project>

    @GET("api/activeProject")
    suspend fun getActiveProject(
        @Header("Cookie") token: String = "token=",
    ): Response<Project>

    @GET("api/arhiveProjects")
    suspend fun getArchiveProjects(
        @Header("Cookie") token: String = "token=",
    ): Response<List<Project>>
}
