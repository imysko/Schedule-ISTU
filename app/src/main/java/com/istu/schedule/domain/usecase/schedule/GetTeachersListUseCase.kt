package com.istu.schedule.domain.usecase.schedule

import com.istu.schedule.domain.entities.schedule.Teacher
import com.istu.schedule.domain.repository.schedule.TeachersRepository
import javax.inject.Inject

interface GetTeachersListUseCase {

    suspend operator fun invoke(): Result<List<Teacher>>
}

class GetTeachersListUseCaseImpl @Inject constructor(
    private val teachersRepository: TeachersRepository,
) : GetTeachersListUseCase {

    override suspend fun invoke(): Result<List<Teacher>> {
        return teachersRepository.getTeachers()
    }
}
