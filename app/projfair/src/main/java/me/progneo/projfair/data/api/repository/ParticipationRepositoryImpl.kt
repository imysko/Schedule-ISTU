package me.progneo.projfair.data.api.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import me.progneo.projfair.data.api.service.ParticipationService
import me.progneo.projfair.data.exception.RequestException
import me.progneo.projfair.domain.model.Participation
import me.progneo.projfair.domain.model.PriorityRequest
import me.progneo.projfair.domain.repository.ParticipationRepository

internal class ParticipationRepositoryImpl @Inject constructor(
    private val participationService: ParticipationService
) : ParticipationRepository {

    private val _isParticipationSent = MutableStateFlow(true)

    override suspend fun getParticipationList(): Result<List<Participation>> {
        val apiResponse = participationService.getParticipationList()

        if (apiResponse.code() == HttpURLConnection.HTTP_OK) {
            apiResponse.body()?.let { participationList ->
                val sortedParticipationList = participationList
                    .sortedBy { participation ->
                        participation.priority
                    }
                    .filter { participation ->
                        participation.state.id in 1..2
                    }
                _isParticipationSent.tryEmit(sortedParticipationList.isNotEmpty())

                return Result.success(sortedParticipationList)
            }
        }

        return Result.failure(
            RequestException(
                code = apiResponse.code(),
                message = apiResponse.message()
            )
        )
    }

    override suspend fun createParticipation(
        projectId: Int,
        priority: PriorityRequest
    ): Result<Unit> {
        val apiResponse = participationService.createParticipation(projectId, priority)

        if (apiResponse.code() == HttpURLConnection.HTTP_OK) {
            return Result.success(Unit)
        }

        return Result.failure(
            RequestException(
                code = apiResponse.code(),
                message = apiResponse.message()
            )
        )
    }

    override suspend fun editParticipation(
        participationId: Int,
        priority: PriorityRequest
    ): Result<Unit> {
        val apiResponse =
            participationService.editParticipation(participationId, priority).body()
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
        participationId: Int
    ): Result<Unit> {
        val apiResponse = participationService.deleteParticipation(participationId).body()
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

    override suspend fun isParticipationSent(): Flow<Boolean> = _isParticipationSent
}
