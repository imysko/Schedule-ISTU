package com.istu.schedule.data.repository.schedule

import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.service.schedule.GroupsService
import com.istu.schedule.domain.model.schedule.Group
import com.istu.schedule.domain.repository.schedule.GroupsRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class GroupsRepositoryImpl @Inject constructor(
    private val groupsService: GroupsService
) : GroupsRepository {

    private val cachedList: MutableList<Group> = mutableListOf()

    override suspend fun getGroups(): Result<List<Group>> {
        val apiResponse = groupsService.getGroups().body()

        if (apiResponse != null) {
            cachedList.addAll(apiResponse)
            return Result.success(apiResponse)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!",
            ),
        )
    }
}