package com.istu.schedule.domain.repository.projfair

import com.istu.schedule.data.api.entities.projfair.request.PriorityRequest
import com.istu.schedule.domain.entities.projfair.Participation

interface ParticipationsRepository {
    suspend fun getParticipationsList(
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
