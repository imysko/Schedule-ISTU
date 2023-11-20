package com.istu.schedule.domain.usecase.schedule

import com.istu.schedule.domain.entities.schedule.Institute
import com.istu.schedule.domain.repository.schedule.InstitutesRepository
import javax.inject.Inject

interface GetInstitutesListUseCase {

    suspend operator fun invoke(): Result<List<Institute>>
}

class GetInstitutesListUseCaseImpl @Inject constructor(
    private val institutesRepository: InstitutesRepository,
) : GetInstitutesListUseCase {

    override suspend fun invoke(): Result<List<Institute>> {
        return institutesRepository.getInstitutes()
    }
}
