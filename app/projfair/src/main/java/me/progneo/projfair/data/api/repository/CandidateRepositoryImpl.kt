package me.progneo.projfair.data.api.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import me.progneo.projfair.data.api.service.CandidateService
import me.progneo.projfair.data.exception.RequestException
import me.progneo.projfair.domain.model.Candidate
import me.progneo.projfair.domain.repository.CandidateRepository

internal class CandidateRepositoryImpl @Inject constructor(
    private val candidateService: CandidateService
) : CandidateRepository {

    private val _canCandidateSendApplications = MutableStateFlow(false)

    override suspend fun getCandidate(): Result<Candidate> {
        val apiResponse = candidateService.getCandidate()

        if (apiResponse.code() == HttpURLConnection.HTTP_OK) {
            apiResponse.body()?.let { candidate ->
                _canCandidateSendApplications.tryEmit(candidate.canSendParticipations == 1)
                return Result.success(candidate)
            }
        }

        return Result.failure(
            RequestException(
                code = apiResponse.code(),
                message = apiResponse.message()
            )
        )
    }

    override suspend fun getProjectCandidatesList(projectId: Int): Result<List<Candidate>> {
        val apiResponse = candidateService.getProjectCandidatesList(projectId).body()
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

    override suspend fun canCandidateSendApplications(): Flow<Boolean> =
        _canCandidateSendApplications
}
