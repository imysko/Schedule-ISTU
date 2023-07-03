package com.istu.schedule.domain.usecase.schedule

import com.istu.schedule.domain.model.schedule.Classroom
import com.istu.schedule.domain.repository.schedule.ClassroomsRepository
import javax.inject.Inject

class GetClassroomsListUseCase @Inject constructor(
    private val classroomsRepository: ClassroomsRepository,
) {

    suspend fun getClassroomsList(): Result<List<Classroom>> {
        return classroomsRepository.getClassrooms()
    }
}