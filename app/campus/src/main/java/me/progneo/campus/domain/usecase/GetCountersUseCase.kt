package me.progneo.campus.domain.usecase

import javax.inject.Inject
import me.progneo.campus.domain.entities.Counters
import me.progneo.campus.domain.repository.CountersRepository

interface GetCountersUseCase {

    suspend operator fun invoke(): Result<Counters>
}

internal class GetCountersUseCaseImpl @Inject constructor(
    private val countersRepository: CountersRepository
) : GetCountersUseCase {

    override suspend operator fun invoke(): Result<Counters> {
        return countersRepository.getCounters()
    }
}
