package com.istu.schedule.data.repository.schedule

import com.istu.schedule.data.database.dao.schedule.FavoriteClassroomsDao
import com.istu.schedule.data.mappers.dao.schedule.mapToDao
import com.istu.schedule.data.mappers.dao.schedule.mapToDomain
import com.istu.schedule.domain.entities.schedule.Classroom
import com.istu.schedule.domain.repository.schedule.FavoriteClassroomsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteClassroomsRepositoryImpl @Inject constructor(
    private val favoriteClassroomsDao: FavoriteClassroomsDao
) : FavoriteClassroomsRepository {

    override fun getAll(): Flow<List<Classroom>> {
        return favoriteClassroomsDao.getAll().map { list ->
            list.map { it.mapToDomain() }
        }
    }

    override suspend fun insert(classroom: Classroom) {
        favoriteClassroomsDao.insert(classroom.mapToDao())
    }

    override suspend fun delete(classroom: Classroom) {
        favoriteClassroomsDao.delete(classroom.mapToDao())
    }
}
