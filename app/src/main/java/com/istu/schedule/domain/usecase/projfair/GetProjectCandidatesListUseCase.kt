package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.domain.model.projfair.Candidate
import com.istu.schedule.domain.repository.projfair.CandidateRepository
import javax.inject.Inject

class GetProjectCandidatesListUseCase @Inject constructor(
    private val candidateRepository: CandidateRepository,
) {

    suspend fun getProjectCandidatesList(projectId: Int): Result<List<Candidate>> {
        return candidateRepository.getProjectCandidatesList(projectId)
    }
}
