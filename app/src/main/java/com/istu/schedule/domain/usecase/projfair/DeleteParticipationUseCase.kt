package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.domain.repository.projfair.ParticipationsRepository
import javax.inject.Inject

class DeleteParticipationUseCase @Inject constructor(
    private val participationsRepository: ParticipationsRepository
) {

    suspend fun deleteParticipation(
        token: String,
        participationId: Int
    ): Result<Unit> {
        return participationsRepository.deleteParticipation(
            token = token,
            participationId = participationId
        )
    }
}
