package com.istu.schedule.domain.repository.projfair

import com.istu.schedule.domain.model.projfair.Candidate
import com.istu.schedule.domain.model.projfair.Project

interface CandidateRepository {
    suspend fun getCandidate(token: String): Result<Candidate>
}