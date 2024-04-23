package me.progneo.projfair.data.api.service

import me.progneo.projfair.domain.model.Project
import me.progneo.projfair.domain.model.ProjectsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ProjectsService {
    @GET("api/projects/filter")
    suspend fun getProjectList(
        @Query("title") title: String = "",
        @Query("page") page: Int = 0,
        @Query("difficulty") difficulties: String = "",
        @Query("state") states: String = "",
        @Query("specialties") specialties: String = "",
        @Query("skills") skills: String = "",
        @Query("order") order: String = "asc",
        @Query("sortBy") sortBy: String = "state"
    ): Response<ProjectsResponse>

    @GET("api/projects/{id}")
    suspend fun getProject(
        @Path("id") id: Int = 0
    ): Response<Project>

    @GET("api/activeProject")
    suspend fun getActiveProject(): Response<Project>

    @GET("api/arhiveProjects")
    suspend fun getArchiveProjectList(): Response<List<Project>>
}
