package com.istu.schedule.domain.repository.schedule

import com.istu.schedule.domain.entities.schedule.Group
import kotlinx.coroutines.flow.Flow

interface FavoriteGroupsRepository {

    fun getAll(): Flow<List<Group>>

    fun getAllActive(): Flow<List<Group>>

    suspend fun insert(group: Group)

    suspend fun delete(group: Group)
}
