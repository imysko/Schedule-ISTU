package com.istu.schedule.domain.usecase.schedule

import com.istu.schedule.domain.model.schedule.Institute
import com.istu.schedule.domain.repository.schedule.InstitutesRepository
import javax.inject.Inject

class GetInstitutesListUseCase @Inject constructor(
    private val institutesRepository: InstitutesRepository
) {

    suspend fun getInstitutesList(): Result<List<Institute>> {
        return institutesRepository.getInstitutes()
    }
}