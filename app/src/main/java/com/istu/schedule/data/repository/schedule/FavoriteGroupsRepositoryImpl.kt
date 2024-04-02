package com.istu.schedule.data.repository.schedule

import com.istu.schedule.data.database.dao.schedule.FavoriteGroupsDao
import com.istu.schedule.data.mappers.dao.schedule.mapToDao
import com.istu.schedule.data.mappers.dao.schedule.mapToDomain
import com.istu.schedule.domain.entities.schedule.Group
import com.istu.schedule.domain.repository.schedule.FavoriteGroupsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteGroupsRepositoryImpl @Inject constructor(
    private val favoriteGroupsDao: FavoriteGroupsDao
) : FavoriteGroupsRepository {

    override fun getAll(): Flow<List<Group>> {
        return favoriteGroupsDao.getAll().map { list ->
            list.map { it.mapToDomain() }
        }
    }

    override fun getAllActive(): Flow<List<Group>> {
        return favoriteGroupsDao.getAllActive().map { list ->
            list.map { it.mapToDomain() }
        }
    }

    override suspend fun insert(group: Group) {
        favoriteGroupsDao.insert(group.mapToDao())
    }

    override suspend fun delete(group: Group) {
        favoriteGroupsDao.delete(group.mapToDao())
    }
}
