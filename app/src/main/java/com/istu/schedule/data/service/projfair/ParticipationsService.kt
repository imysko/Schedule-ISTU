package com.istu.schedule.data.service.projfair

import com.istu.schedule.domain.model.projfair.Participation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ParticipationsService {
    @GET("api/participations")
    suspend fun getParticipationsList(
        @Header("Cookie") token: String = "token=",
    ): Response<List<Participation>>
}
