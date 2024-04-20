package me.progneo.campus.data.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import me.progneo.campus.data.exception.RequestException
import me.progneo.campus.data.service.CountersService
import me.progneo.campus.domain.entities.BaseDataResponse
import me.progneo.campus.domain.entities.CountersResponse
import me.progneo.campus.domain.repository.CountersRepository

internal class CountersRepositoryImpl @Inject constructor(
    private val countersService: CountersService
) : CountersRepository {

    override suspend fun getCounters(token: String): Result<BaseDataResponse<CountersResponse>> {
        val apiResponse = countersService.getCounters(token).body()
        if (apiResponse != null) {
            return Result.success(apiResponse)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }
}
