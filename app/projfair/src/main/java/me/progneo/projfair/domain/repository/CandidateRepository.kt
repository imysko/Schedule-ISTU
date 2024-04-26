package me.progneo.projfair.domain.repository

import kotlinx.coroutines.flow.Flow
import me.progneo.projfair.domain.model.Candidate

interface CandidateRepository {

    suspend fun getCandidate(): Result<Candidate>

    suspend fun getProjectCandidatesList(projectId: Int): Result<List<Candidate>>

    suspend fun canCandidateSendApplications(): Flow<Boolean>
}
