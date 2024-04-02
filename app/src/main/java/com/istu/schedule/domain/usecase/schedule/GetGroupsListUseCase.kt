package com.istu.schedule.domain.usecase.schedule

import com.istu.schedule.domain.entities.schedule.Group
import com.istu.schedule.domain.repository.schedule.GroupsRepository
import javax.inject.Inject

interface GetGroupsListUseCase {

    suspend operator fun invoke(): Result<List<Group>>
}

class GetGroupsListUseCaseImpl @Inject constructor(
    private val groupsRepository: GroupsRepository
) : GetGroupsListUseCase {

    override suspend fun invoke(): Result<List<Group>> {
        return groupsRepository.getGroups()
    }
}
