package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.domain.model.projfair.ProjectState
import com.istu.schedule.domain.repository.projfair.ProjectStateRepository
import javax.inject.Inject

class GetProjectStateUseCase @Inject constructor(
    private val projectStateRepository: ProjectStateRepository
) {

    suspend fun getProjectState(id: Int): Result<ProjectState> {
        return projectStateRepository.getProjectState(id)
    }
}