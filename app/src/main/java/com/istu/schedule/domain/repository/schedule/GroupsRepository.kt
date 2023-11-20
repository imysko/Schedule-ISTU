package com.istu.schedule.domain.repository.schedule

import com.istu.schedule.domain.entities.schedule.Group

interface GroupsRepository {
    suspend fun getGroups(): Result<List<Group>>
}
