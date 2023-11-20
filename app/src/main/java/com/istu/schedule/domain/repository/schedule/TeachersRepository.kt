package com.istu.schedule.domain.repository.schedule

import com.istu.schedule.domain.entities.schedule.Teacher

interface TeachersRepository {
    suspend fun getTeachers(): Result<List<Teacher>>
}
