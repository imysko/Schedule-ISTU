package me.progneo.projfair.data.service

import me.progneo.projfair.domain.model.Participation
import me.progneo.projfair.domain.model.PriorityRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

internal interface ParticipationService {
    @GET("api/participations")
    suspend fun getParticipationList(
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
