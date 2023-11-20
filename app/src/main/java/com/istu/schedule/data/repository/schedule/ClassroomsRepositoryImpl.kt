package com.istu.schedule.data.repository.schedule

import com.istu.schedule.data.api.service.schedule.ClassroomsService
import com.istu.schedule.data.mappers.api.schedule.mapToDomain
import com.istu.schedule.data.api.entities.RequestException
import com.istu.schedule.domain.entities.schedule.Classroom
import com.istu.schedule.domain.repository.schedule.ClassroomsRepository
import javax.inject.Inject

class ClassroomsRepositoryImpl @Inject constructor(
    private val classroomsService: ClassroomsService
) : ClassroomsRepository {

    private val cachedList: MutableList<Classroom> = mutableListOf()

    override suspend fun getClassrooms(): Result<List<Classroom>> {
        val apiResponse = classroomsService.getClassrooms()

        apiResponse.body()?.let { classroomListApi ->
            return Result.success(
                classroomListApi
                    .map { it.mapToDomain() }
                    .also { cachedList.addAll(it)}
            )
        } ?: kotlin.run {
            return Result.failure(
                RequestException(
                    code = apiResponse.code(),
                    message = apiResponse.message(),
                )
            )
        }
    }
}
