package me.progneo.projfair.domain.repository

import me.progneo.projfair.domain.model.Participation
import me.progneo.projfair.domain.model.PriorityRequest

interface ParticipationRepository {
    suspend fun getParticipationList(
        token: String
    ): Result<List<Participation>>

    suspend fun createParticipation(
        token: String,
        projectId: Int,
        priority: PriorityRequest
    ): Result<Unit>

    suspend fun editParticipation(
        token: String,
        participationId: Int,
        priority: PriorityRequest
    ): Result<Unit>

    suspend fun deleteParticipation(
        token: String,
        participationId: Int
    ): Result<Unit>
}
