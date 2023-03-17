package com.istu.schedule.data.service.projfair

import com.istu.schedule.domain.model.projfair.Candidate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CandidateService {
    @GET("api/candidate")
    suspend fun getCandidate(
        @Header("Cookie") token: String = ""
    ): Response<Candidate>
}