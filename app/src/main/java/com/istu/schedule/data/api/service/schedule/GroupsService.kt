package com.istu.schedule.data.api.service.schedule

import com.istu.schedule.data.api.entities.schedule.GroupResponse
import retrofit2.Response
import retrofit2.http.GET

interface GroupsService {
    @GET("schedule-api/groups")
    suspend fun getGroups(): Response<List<GroupResponse>>
}
