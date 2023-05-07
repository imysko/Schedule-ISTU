package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.domain.repository.projfair.ParticipationsRepository
import javax.inject.Inject

class CreateParticipationUseCase @Inject constructor(
    private val participationsRepository: ParticipationsRepository
) {

    suspend fun createParticipation(
        token: String,
        projectId: Int
    ): Result<Unit> {
        return participationsRepository.createParticipation(
            token = token,
            projectId = projectId
        )
    }
}
