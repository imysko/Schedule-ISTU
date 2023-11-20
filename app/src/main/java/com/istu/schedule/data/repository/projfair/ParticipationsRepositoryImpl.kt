package com.istu.schedule.data.repository.projfair

import com.istu.schedule.data.api.entities.RequestException
import com.istu.schedule.data.api.entities.projfair.request.PriorityRequest
import com.istu.schedule.data.api.service.projfair.ParticipationsService
import com.istu.schedule.domain.entities.projfair.Participation
import com.istu.schedule.domain.repository.projfair.ParticipationsRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class ParticipationsRepositoryImpl @Inject constructor(
    private val participationsService: ParticipationsService
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
                message = "An error occurred!"
            )
        )
    }

    override suspend fun createParticipation(
        token: String,
        projectId: Int,
        priority: PriorityRequest
    ): Result<Unit> {
        val apiResponse =
            participationsService.createParticipation(token, projectId, priority).body()
        if (apiResponse != null) {
            return Result.success(Unit)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }

    override suspend fun editParticipation(
        token: String,
        participationId: Int,
        priority: PriorityRequest
    ): Result<Unit> {
        val apiResponse =
            participationsService.editParticipation(token, participationId, priority).body()
        if (apiResponse != null) {
            return Result.success(Unit)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }

    override suspend fun deleteParticipation(
        token: String,
        participationId: Int
    ): Result<Unit> {
        val apiResponse = participationsService.deleteParticipation(token, participationId).body()
        if (apiResponse != null) {
            return Result.success(Unit)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }
}
