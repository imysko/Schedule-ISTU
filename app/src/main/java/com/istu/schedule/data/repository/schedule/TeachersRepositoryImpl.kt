package com.istu.schedule.data.repository.schedule

import com.istu.schedule.data.api.entities.RequestException
import com.istu.schedule.data.api.service.schedule.TeachersService
import com.istu.schedule.data.mappers.api.schedule.mapToDomain
import com.istu.schedule.domain.entities.schedule.Teacher
import com.istu.schedule.domain.repository.schedule.TeachersRepository
import javax.inject.Inject

class TeachersRepositoryImpl @Inject constructor(
    private val teachersService: TeachersService
) : TeachersRepository {

    private val cachedList: MutableList<Teacher> = mutableListOf()

    override suspend fun getTeachers(): Result<List<Teacher>> {
        val apiResponse = teachersService.getTeachers()

        apiResponse.body()?.let { teacherListApi ->
            return Result.success(
                teacherListApi
                    .map { it.mapToDomain() }
                    .also { cachedList.addAll(it) }
            )
        } ?: kotlin.run {
            return Result.failure(
                RequestException(
                    code = apiResponse.code(),
                    message = apiResponse.message()
                )
            )
        }
    }
}
