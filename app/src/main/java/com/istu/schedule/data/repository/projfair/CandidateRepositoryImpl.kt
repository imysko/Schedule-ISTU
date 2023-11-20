package com.istu.schedule.data.repository.projfair

import com.istu.schedule.data.api.entities.RequestException
import com.istu.schedule.data.api.service.projfair.CandidateService
import com.istu.schedule.domain.entities.projfair.Candidate
import com.istu.schedule.domain.repository.projfair.CandidateRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class CandidateRepositoryImpl @Inject constructor(
    private val candidateService: CandidateService,
) : CandidateRepository {

    override suspend fun getCandidate(token: String): Result<Candidate> {
        val apiResponse = candidateService.getCandidate(token).body()
        if (apiResponse != null) {
            return Result.success(apiResponse)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!",
            ),
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
                message = "An error occurred!",
            ),
        )
    }
}
