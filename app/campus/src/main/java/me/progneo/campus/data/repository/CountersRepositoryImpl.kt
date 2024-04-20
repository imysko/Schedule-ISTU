package me.progneo.campus.data.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import me.progneo.campus.data.exception.RequestException
import me.progneo.campus.data.service.CountersService
import me.progneo.campus.domain.entities.Counters
import me.progneo.campus.domain.repository.CountersRepository

internal class CountersRepositoryImpl @Inject constructor(
    private val countersService: CountersService
) : CountersRepository {

    override suspend fun getCounters(token: String): Result<Counters> {
        val apiResponse = countersService.getCounters(token)

        if (apiResponse.code() == HttpURLConnection.HTTP_OK) {
            apiResponse.body()?.let { response ->
                return Result.success(response.result.counters)
            }
        }

        return Result.failure(
            RequestException(
                code = apiResponse.code(),
                message = apiResponse.message()
            )
        )
    }
}
