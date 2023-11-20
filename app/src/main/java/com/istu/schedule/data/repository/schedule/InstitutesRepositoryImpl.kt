package com.istu.schedule.data.repository.schedule

import com.istu.schedule.data.api.service.schedule.InstitutesService
import com.istu.schedule.data.mappers.api.schedule.mapToDomain
import com.istu.schedule.data.api.entities.RequestException
import com.istu.schedule.domain.entities.schedule.Institute
import com.istu.schedule.domain.repository.schedule.InstitutesRepository
import javax.inject.Inject

class InstitutesRepositoryImpl @Inject constructor(
    private val institutesService: InstitutesService,
) : InstitutesRepository {

    private val cachedList: MutableList<Institute> = mutableListOf()

    override suspend fun getInstitutes(): Result<List<Institute>> {
        val apiResponse = institutesService.getInstitutes()

        apiResponse.body()?.let { instituteListApi ->
            return Result.success(
                instituteListApi
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
