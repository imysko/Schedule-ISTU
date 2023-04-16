package com.istu.schedule.domain.repository.projfair

import com.istu.schedule.domain.model.projfair.ProjectState

interface ProjectStateRepository {
    suspend fun getProjectStatesList(): Result<List<ProjectState>>
    suspend fun getProjectState(id: Int): Result<ProjectState>
}
