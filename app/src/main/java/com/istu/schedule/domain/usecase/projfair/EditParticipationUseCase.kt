package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.data.model.request.PriorityRequest
import com.istu.schedule.domain.repository.projfair.ParticipationsRepository
import javax.inject.Inject

class EditParticipationUseCase @Inject constructor(
    private val participationsRepository: ParticipationsRepository
) {

    suspend fun editParticipation(
        token: String,
        participationId: Int,
        priority: PriorityRequest
    ): Result<Unit> {
        return participationsRepository.editParticipation(
            token = token,
            participationId = participationId,
            priority = priority
        )
    }
}
