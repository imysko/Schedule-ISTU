package me.progneo.projfair.data.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import me.progneo.projfair.data.exception.RequestException
import me.progneo.projfair.data.service.ParticipationService
import me.progneo.projfair.domain.model.Participation
import me.progneo.projfair.domain.model.PriorityRequest
import me.progneo.projfair.domain.repository.ParticipationRepository

internal class ParticipationRepositoryImpl @Inject constructor(
    private val participationService: ParticipationService
) : ParticipationRepository {

    private val cachedList: MutableList<Participation> = mutableListOf()

    override suspend fun getParticipationList(token: String): Result<List<Participation>> {
        val apiResponse = participationService.getParticipationList(token).body()
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
            participationService.createParticipation(token, projectId, priority).body()
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
            participationService.editParticipation(token, participationId, priority).body()
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
        val apiResponse = participationService.deleteParticipation(token, participationId).body()
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
