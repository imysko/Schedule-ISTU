package com.istu.schedule.domain.repository.schedule

import com.istu.schedule.domain.model.schedule.Teacher

interface TeachersRepository {
    suspend fun getTeachers(): Result<List<Teacher>>
}