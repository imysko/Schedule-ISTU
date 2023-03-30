package com.istu.schedule.data.repository.schedule

import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.service.schedule.TeachersService
import com.istu.schedule.domain.model.schedule.Teacher
import com.istu.schedule.domain.repository.schedule.TeachersRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class TeachersRepositoryImpl @Inject constructor(
    private val teachersService: TeachersService
) : TeachersRepository {

    private val cachedList: MutableList<Teacher> = mutableListOf()

    override suspend fun getTeachers(): Result<List<Teacher>> {
        val apiResponse = teachersService.getTeachers().body()

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