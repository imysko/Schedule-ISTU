package me.progneo.projfair.domain.usecase

import javax.inject.Inject
import me.progneo.projfair.domain.repository.ParticipationRepository

class DeleteParticipationUseCase @Inject constructor(
    private val participationRepository: ParticipationRepository
) {

    suspend operator fun invoke(participationId: Int): Result<Unit> {
        return participationRepository.deleteParticipation(
            participationId = participationId
        )
    }
}
