package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.domain.entities.projfair.ProjectState
import com.istu.schedule.domain.repository.projfair.ProjectStateRepository
import javax.inject.Inject

class GetProjectStatesListUseCase @Inject constructor(
    private val projectStateRepository: ProjectStateRepository,
) {

    suspend fun getProjectStatesListList(): Result<List<ProjectState>> {
        return projectStateRepository.getProjectStatesList()
    }
}
