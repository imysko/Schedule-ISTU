package com.istu.schedule.domain.repository.schedule

import com.istu.schedule.domain.entities.schedule.Teacher
import kotlinx.coroutines.flow.Flow

interface FavoriteTeachersRepository {

    fun getAll(): Flow<List<Teacher>>

    suspend fun insert(teacher: Teacher)

    suspend fun delete(teacher: Teacher)
}
