package me.progneo.campus.domain.usecase

import javax.inject.Inject
import me.progneo.campus.domain.entities.BaseDataResponse
import me.progneo.campus.domain.entities.CountersResponse
import me.progneo.campus.domain.repository.CountersRepository

interface GetCountersUseCase {

    suspend operator fun invoke(token: String): Result<BaseDataResponse<CountersResponse>>
}

internal class GetCountersUseCaseImpl @Inject constructor(
    private val countersRepository: CountersRepository
) : GetCountersUseCase {

    override suspend operator fun invoke(
        token: String
    ): Result<BaseDataResponse<CountersResponse>> {
        return countersRepository.getCounters(
            token = token
        )
    }
}
