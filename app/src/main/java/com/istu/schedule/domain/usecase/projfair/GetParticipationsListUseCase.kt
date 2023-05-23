package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.domain.model.projfair.Participation
import com.istu.schedule.domain.repository.projfair.ParticipationsRepository
import javax.inject.Inject

class GetParticipationsListUseCase @Inject constructor(
    private val participationsRepository: ParticipationsRepository
) {

    suspend fun getParticipationsList(token: String): Result<List<Participation>> {
        return participationsRepository.getParticipationsList(token)
    }
}
