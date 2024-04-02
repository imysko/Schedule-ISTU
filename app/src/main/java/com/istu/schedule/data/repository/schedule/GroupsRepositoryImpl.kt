package com.istu.schedule.data.repository.schedule

import com.istu.schedule.data.api.entities.RequestException
import com.istu.schedule.data.api.service.schedule.GroupsService
import com.istu.schedule.data.mappers.api.schedule.mapToDomain
import com.istu.schedule.domain.entities.schedule.Group
import com.istu.schedule.domain.repository.schedule.GroupsRepository
import javax.inject.Inject

class GroupsRepositoryImpl @Inject constructor(
    private val groupsService: GroupsService
) : GroupsRepository {

    private val cachedList: MutableList<Group> = mutableListOf()

    override suspend fun getGroups(): Result<List<Group>> {
        val apiResponse = groupsService.getGroups()

        apiResponse.body()?.let { groupListApi ->
            return Result.success(
                groupListApi
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
