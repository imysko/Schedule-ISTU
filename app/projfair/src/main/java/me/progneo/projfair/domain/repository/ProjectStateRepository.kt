package me.progneo.projfair.domain.repository

import me.progneo.projfair.domain.model.ProjectState

interface ProjectStateRepository {
    suspend fun getProjectStatesList(): Result<List<ProjectState>>
    suspend fun getProjectState(id: Int): Result<ProjectState>
}
