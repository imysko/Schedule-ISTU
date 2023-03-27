package com.istu.schedule.data.repository.projfair

import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.service.projfair.ParticipationsService
import com.istu.schedule.domain.model.projfair.Participation
import com.istu.schedule.domain.repository.projfair.ParticipationsRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class ParticipationsRepositoryImpl @Inject constructor(
    private val participationsService: ParticipationsService,
) : ParticipationsRepository {

    private val cachedList: MutableList<Participation> = mutableListOf()

    override suspend fun getParticipationsList(token: String): Result<List<Participation>> {
        val apiResponse = participationsService.getParticipationsList(token).body()
        if (apiResponse != null) {
            cachedList.addAll(apiResponse)
            return Result.success(apiResponse)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!",
            ),
        )
    }
}
