package me.progneo.campus.data.api.service

import me.progneo.campus.domain.entities.BaseDataResponse
import me.progneo.campus.domain.entities.CountersResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface CountersService {

    @GET("im.counters.get")
    suspend fun getCounters(): Response<BaseDataResponse<CountersResponse>>
}
