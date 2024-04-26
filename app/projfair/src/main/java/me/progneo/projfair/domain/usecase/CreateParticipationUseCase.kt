package me.progneo.projfair.domain.usecase

import javax.inject.Inject
import me.progneo.projfair.domain.model.PriorityRequest
import me.progneo.projfair.domain.repository.ParticipationRepository

class CreateParticipationUseCase @Inject constructor(
    private val participationRepository: ParticipationRepository
) {

    suspend operator fun invoke(
        projectId: Int,
        priority: PriorityRequest
    ): Result<Unit> {
        return participationRepository.createParticipation(
            projectId = projectId,
            priority = priority
        )
    }
}
