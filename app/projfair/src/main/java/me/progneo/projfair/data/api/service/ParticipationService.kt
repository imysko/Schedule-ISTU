package me.progneo.projfair.data.api.service

import me.progneo.projfair.domain.model.Participation
import me.progneo.projfair.domain.model.PriorityRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

internal interface ParticipationService {
    @GET("api/participations")
    suspend fun getParticipationList(): Response<List<Participation>>

    @POST("api/participations/{id}")
    suspend fun createParticipation(
        @Path("id") projectId: Int = 0,
        @Body body: PriorityRequest
    ): Response<Unit>

    @DELETE("api/participations/{id}")
    suspend fun deleteParticipation(
        @Path("id") participationId: Int = 0
    ): Response<Unit>

    @PATCH("api/participations/{id}")
    suspend fun editParticipation(
        @Path("id") projectId: Int = 0,
        @Body body: PriorityRequest
    ): Response<Unit>
}
