package com.istu.schedule.domain.repository.schedule

import com.istu.schedule.domain.entities.schedule.Classroom

interface ClassroomsRepository {
    suspend fun getClassrooms(): Result<List<Classroom>>
}
