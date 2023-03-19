package com.istu.schedule.domain.repository.projfair

import com.istu.schedule.domain.model.projfair.Project

interface ProjectsRepository {
    suspend fun getProjects(page: Int): Result<List<Project>>
    suspend fun getProject(id: Int): Result<Project>
}