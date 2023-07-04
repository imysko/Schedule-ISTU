package com.istu.schedule.data.service.schedule

import com.istu.schedule.domain.model.schedule.Group
import retrofit2.Response
import retrofit2.http.GET

interface GroupsService {
    @GET("schedule-api/groups")
    suspend fun getGroups(): Response<List<Group>>
}
