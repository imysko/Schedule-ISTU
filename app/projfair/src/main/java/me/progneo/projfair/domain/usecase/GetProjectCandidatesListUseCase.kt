package me.progneo.projfair.domain.usecase

import javax.inject.Inject
import me.progneo.projfair.domain.model.Candidate
import me.progneo.projfair.domain.repository.CandidateRepository

class GetProjectCandidatesListUseCase @Inject constructor(
    private val candidateRepository: CandidateRepository
) {

    suspend operator fun invoke(projectId: Int): Result<List<Candidate>> {
        return candidateRepository.getProjectCandidatesList(projectId)
    }
}
