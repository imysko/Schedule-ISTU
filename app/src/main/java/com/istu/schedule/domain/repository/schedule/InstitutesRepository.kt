package com.istu.schedule.domain.repository.schedule

import com.istu.schedule.domain.model.schedule.Institute

interface InstitutesRepository {
    suspend fun getInstitutes(): Result<List<Institute>>
}
