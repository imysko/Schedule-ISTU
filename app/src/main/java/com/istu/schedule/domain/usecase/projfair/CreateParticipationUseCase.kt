package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.data.model.request.PriorityRequest
import com.istu.schedule.domain.repository.projfair.ParticipationsRepository
import javax.inject.Inject

class CreateParticipationUseCase @Inject constructor(
    private val participationsRepository: ParticipationsRepository
) {

    suspend fun createParticipation(
        token: String,
        projectId: Int,
        priority: PriorityRequest
    ): Result<Unit> {
        return participationsRepository.createParticipation(
            token = token,
            projectId = projectId,
            priority = priority
        )
    }
}
