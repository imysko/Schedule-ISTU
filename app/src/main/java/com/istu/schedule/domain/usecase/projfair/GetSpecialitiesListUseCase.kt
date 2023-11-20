package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.domain.entities.projfair.Speciality
import com.istu.schedule.domain.repository.projfair.FiltersDataRepository
import javax.inject.Inject

class GetSpecialitiesListUseCase @Inject constructor(
    private val filtersDataRepository: FiltersDataRepository,
) {

    suspend fun getSpecialitiesList(token: String): Result<List<Speciality>> {
        return filtersDataRepository.getSpecialities(token)
    }
}
