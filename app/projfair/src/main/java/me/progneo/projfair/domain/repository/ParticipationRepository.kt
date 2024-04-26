package me.progneo.projfair.domain.repository

import kotlinx.coroutines.flow.Flow
import me.progneo.projfair.domain.model.Participation
import me.progneo.projfair.domain.model.PriorityRequest

interface ParticipationRepository {
    suspend fun getParticipationList(): Result<List<Participation>>

    suspend fun createParticipation(
        projectId: Int,
        priority: PriorityRequest
    ): Result<Unit>

    suspend fun editParticipation(
        participationId: Int,
        priority: PriorityRequest
    ): Result<Unit>

    suspend fun deleteParticipation(
        participationId: Int
    ): Result<Unit>

    suspend fun isParticipationSent(): Flow<Boolean>
}
