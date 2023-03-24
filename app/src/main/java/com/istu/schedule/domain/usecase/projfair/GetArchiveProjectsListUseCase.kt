package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.domain.repository.projfair.ProjectsRepository
import javax.inject.Inject

class GetArchiveProjectsListUseCase @Inject constructor(
    private val projectsRepository: ProjectsRepository
) {

    suspend fun getArchiveProjectsList(token: String): Result<List<Project>> {
        return projectsRepository.getArchiveProjects(token)
    }
}