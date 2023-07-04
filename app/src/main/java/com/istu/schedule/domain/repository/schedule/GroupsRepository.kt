package com.istu.schedule.domain.repository.schedule

import com.istu.schedule.domain.model.schedule.Group

interface GroupsRepository {
    suspend fun getGroups(): Result<List<Group>>
}
