package me.progneo.campus.data.service

import me.progneo.campus.domain.entities.BaseDataResponse
import me.progneo.campus.domain.entities.CountersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CountersService {

    @GET("im.counters.get")
    suspend fun getCounters(
        @Query("auth") token: String
    ): Response<BaseDataResponse<CountersResponse>>
}
