package com.istu.schedule.domain.repository.projfair

import com.istu.schedule.domain.model.projfair.Candidate

interface CandidateRepository {
    suspend fun getCandidate(token: String): Result<Candidate>
    suspend fun getProjectCandidatesList(projectId: Int): Result<List<Candidate>>
}
