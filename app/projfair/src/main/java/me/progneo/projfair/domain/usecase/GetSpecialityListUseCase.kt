package me.progneo.projfair.domain.usecase

import javax.inject.Inject
import me.progneo.projfair.domain.model.Speciality
import me.progneo.projfair.domain.repository.FiltersDataRepository

class GetSpecialityListUseCase @Inject constructor(
    private val filtersDataRepository: FiltersDataRepository
) {

    suspend operator fun invoke(token: String): Result<List<Speciality>> {
        return filtersDataRepository.getSpecialities(token)
    }
}
