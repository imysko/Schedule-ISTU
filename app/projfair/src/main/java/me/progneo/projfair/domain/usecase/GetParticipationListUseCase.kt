package me.progneo.projfair.domain.usecase

import javax.inject.Inject
import me.progneo.projfair.domain.model.Participation
import me.progneo.projfair.domain.repository.ParticipationRepository

class GetParticipationListUseCase @Inject constructor(
    private val participationRepository: ParticipationRepository
) {

    suspend operator fun invoke(token: String): Result<List<Participation>> {
        return participationRepository.getParticipationList(token)
    }
}
