package me.progneo.projfair.domain.repository

import me.progneo.projfair.domain.model.Candidate

interface CandidateRepository {
    suspend fun getCandidate(token: String): Result<Candidate>
    suspend fun getProjectCandidatesList(projectId: Int): Result<List<Candidate>>
}
