package com.istu.schedule.domain.usecase.schedule

import com.istu.schedule.domain.model.schedule.Group
import com.istu.schedule.domain.repository.schedule.GroupsRepository
import javax.inject.Inject

class GetGroupsListUseCase @Inject constructor(
    private val groupsRepository: GroupsRepository
) {

    suspend fun getGroupsList(): Result<List<Group>> {
        return groupsRepository.getGroups()
    }
}
