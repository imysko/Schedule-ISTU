package me.progneo.campus.domain.repository

import me.progneo.campus.domain.entities.BaseDataResponse
import me.progneo.campus.domain.entities.CountersResponse

interface CountersRepository {

    suspend fun getCounters(token: String): Result<BaseDataResponse<CountersResponse>>
}
