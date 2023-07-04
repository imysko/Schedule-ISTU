package com.istu.schedule.data.repository.schedule

import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.service.schedule.ClassroomsService
import com.istu.schedule.domain.model.schedule.Classroom
import com.istu.schedule.domain.repository.schedule.ClassroomsRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class ClassroomsRepositoryImpl @Inject constructor(
    private val classroomsService: ClassroomsService
) : ClassroomsRepository {

    private val cachedList: MutableList<Classroom> = mutableListOf()
    override suspend fun getClassrooms(): Result<List<Classroom>> {
        val apiResponse = classroomsService.getClassrooms().body()

        if (apiResponse != null) {
            cachedList.addAll(apiResponse)
            return Result.success(apiResponse)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }
}
