package com.istu.schedule.domain.usecase.schedule

import com.istu.schedule.domain.entities.schedule.Classroom
import com.istu.schedule.domain.repository.schedule.ClassroomsRepository
import javax.inject.Inject

interface GetClassroomsListUseCase {

    suspend operator fun invoke(): Result<List<Classroom>>
}

class GetClassroomsListUseCaseImpl @Inject constructor(
    private val classroomsRepository: ClassroomsRepository
) : GetClassroomsListUseCase {

    override suspend fun invoke(): Result<List<Classroom>> {
        return classroomsRepository.getClassrooms()
    }
}
