package com.istu.schedule.domain.usecase.schedule

import com.istu.schedule.domain.model.schedule.Teacher
import com.istu.schedule.domain.repository.schedule.TeachersRepository
import javax.inject.Inject

class GetTeachersListUseCase @Inject constructor(
    private val teachersRepository: TeachersRepository
) {

    suspend fun getTeachersList(): Result<List<Teacher>> {
        return teachersRepository.getTeachers()
    }
}