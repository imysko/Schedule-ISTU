package com.istu.schedule.data.api.service.projfair

import com.istu.schedule.domain.entities.projfair.Candidate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CandidateService {
    @GET("api/candidate")
    suspend fun getCandidate(
        @Header("Cookie") token: String = "token=",
    ): Response<Candidate>

    @GET("api/project/{projectId}/participants")
    suspend fun getProjectCandidatesList(
        @Path("projectId") projectId: Int = 0,
    ): Response<List<Candidate>>
}
