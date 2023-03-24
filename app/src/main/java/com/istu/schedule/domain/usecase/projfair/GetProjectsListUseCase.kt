package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.domain.repository.projfair.ProjectsRepository
import javax.inject.Inject

class GetProjectsListUseCase @Inject constructor(
    private val projectsRepository: ProjectsRepository
) {

    suspend fun getProjectsList(page: Int): Result<List<Project>> {
        return projectsRepository.getProjects(page)
    }
}