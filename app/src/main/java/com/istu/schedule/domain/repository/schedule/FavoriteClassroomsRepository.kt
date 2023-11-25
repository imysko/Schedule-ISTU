package com.istu.schedule.domain.repository.schedule

import com.istu.schedule.domain.entities.schedule.Classroom
import kotlinx.coroutines.flow.Flow

interface FavoriteClassroomsRepository {

    fun getAll(): Flow<List<Classroom>>

    suspend fun insert(classroom: Classroom)

    suspend fun delete(classroom: Classroom)
}
