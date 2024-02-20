package me.progneo.projfair.data.service

import me.progneo.projfair.domain.model.Candidate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

internal interface CandidateService {
    @GET("api/candidate")
    suspend fun getCandidate(
        @Header("Cookie") token: String = "token="
    ): Response<Candidate>

    @GET("api/project/{projectId}/participants")
    suspend fun getProjectCandidatesList(
        @Path("projectId") projectId: Int = 0
    ): Response<List<Candidate>>
}
