package me.progneo.projfair.domain.usecase

import javax.inject.Inject
import me.progneo.projfair.domain.model.PriorityRequest
import me.progneo.projfair.domain.repository.ParticipationRepository

class CreateParticipationUseCase @Inject constructor(
    private val participationRepository: ParticipationRepository
) {

    suspend operator fun invoke(
        token: String,
        projectId: Int,
        priority: PriorityRequest
    ): Result<Unit> {
        return participationRepository.createParticipation(
            token = token,
            projectId = projectId,
            priority = priority
        )
    }
}
