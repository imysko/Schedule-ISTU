package me.progneo.projfair.domain.usecase

import javax.inject.Inject
import me.progneo.projfair.domain.model.Candidate
import me.progneo.projfair.domain.repository.CandidateRepository

class GetCandidateUseCase @Inject constructor(
    private val candidateRepository: CandidateRepository
) {

    suspend operator fun invoke(): Result<Candidate> {
        return candidateRepository.getCandidate()
    }
}
