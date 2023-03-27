package com.istu.schedule.domain.repository.projfair

import com.istu.schedule.domain.model.projfair.Participation

interface ParticipationsRepository {
    suspend fun getParticipationsList(token: String): Result<List<Participation>>
}
