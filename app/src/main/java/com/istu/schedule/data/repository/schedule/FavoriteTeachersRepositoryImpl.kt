package com.istu.schedule.data.repository.schedule

import com.istu.schedule.data.database.dao.schedule.FavoriteTeachersDao
import com.istu.schedule.data.mappers.dao.schedule.mapToDao
import com.istu.schedule.data.mappers.dao.schedule.mapToDomain
import com.istu.schedule.domain.entities.schedule.Teacher
import com.istu.schedule.domain.repository.schedule.FavoriteTeachersRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteTeachersRepositoryImpl @Inject constructor(
    private val favoriteTeachersDao: FavoriteTeachersDao
) : FavoriteTeachersRepository {

    override fun getAll(): Flow<List<Teacher>> {
        return favoriteTeachersDao.getAll().map { list ->
            list.map { it.mapToDomain() }
        }
    }

    override suspend fun insert(teacher: Teacher) {
        favoriteTeachersDao.insert(teacher.mapToDao())
    }

    override suspend fun delete(teacher: Teacher) {
        favoriteTeachersDao.delete(teacher.mapToDao())
    }
}
