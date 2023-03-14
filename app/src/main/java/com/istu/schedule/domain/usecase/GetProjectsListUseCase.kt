package com.istu.schedule.domain.usecase

import com.istu.schedule.domain.model.Project
import com.istu.schedule.domain.repository.ProjectsRepository
import javax.inject.Inject

class GetProjectsListUseCase @Inject constructor(private val projectsRepository: ProjectsRepository) {

    suspend fun getProjectsList(page: Int): Result<List<Project>> {
        return projectsRepository.getProjects(page)
    }
}