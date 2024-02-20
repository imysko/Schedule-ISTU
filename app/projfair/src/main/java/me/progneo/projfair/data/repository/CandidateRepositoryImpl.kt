package me.progneo.projfair.data.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import me.progneo.projfair.data.exception.RequestException
import me.progneo.projfair.data.service.CandidateService
import me.progneo.projfair.domain.model.Candidate
import me.progneo.projfair.domain.repository.CandidateRepository

internal class CandidateRepositoryImpl @Inject constructor(
    private val candidateService: CandidateService
) : CandidateRepository {

    override suspend fun getCandidate(token: String): Result<Candidate> {
        val apiResponse = candidateService.getCandidate(token).body()
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
}
