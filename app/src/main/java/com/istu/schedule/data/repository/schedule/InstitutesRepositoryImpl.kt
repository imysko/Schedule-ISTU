package com.istu.schedule.data.repository.schedule

import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.service.schedule.InstitutesService
import com.istu.schedule.domain.model.schedule.Institute
import com.istu.schedule.domain.repository.schedule.InstitutesRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class InstitutesRepositoryImpl @Inject constructor(
    private val scheduleService: InstitutesService
) : InstitutesRepository {

    private val cachedList: MutableList<Institute> = mutableListOf()

    override suspend fun getInstitutes(): Result<List<Institute>> {
        val apiResponse = scheduleService.getInstitutes().body()

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