package com.istu.schedule.data.service.projfair

import com.istu.schedule.data.model.request.PriorityRequest
import com.istu.schedule.domain.model.projfair.Participation
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ParticipationsService {
    @GET("api/participations")
    suspend fun getParticipationsList(
        @Header("Cookie") token: String = "token="
    ): Response<List<Participation>>

    @POST("api/participations/{id}")
    suspend fun createParticipation(
        @Header("Cookie") token: String = "token=",
        @Path("id") projectId: Int = 0,
        @Body body: PriorityRequest
    ): Response<Unit>

    @DELETE("api/participations/{id}")
    suspend fun deleteParticipation(
        @Header("Cookie") token: String = "token=",
        @Path("id") participationId: Int = 0
    ): Response<Unit>

    @PATCH("api/participations/{id}")
    suspend fun editParticipation(
        @Header("Cookie") token: String = "token=",
        @Path("id") projectId: Int = 0,
        @Body body: PriorityRequest
    ): Response<Unit>
}
