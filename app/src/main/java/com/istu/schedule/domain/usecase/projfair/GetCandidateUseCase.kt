package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.domain.model.projfair.Candidate
import com.istu.schedule.domain.repository.projfair.CandidateRepository
import javax.inject.Inject

class GetCandidateUseCase @Inject constructor(
    private val candidateRepository: CandidateRepository,
) {

    suspend fun getCandidate(token: String): Result<Candidate> {
        return candidateRepository.getCandidate(token)
    }
}
