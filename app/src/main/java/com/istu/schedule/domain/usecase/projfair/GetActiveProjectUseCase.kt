package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.domain.entities.projfair.Project
import com.istu.schedule.domain.repository.projfair.ProjectsRepository
import javax.inject.Inject

class GetActiveProjectUseCase @Inject constructor(
    private val projectsRepository: ProjectsRepository,
) {

    suspend fun getActiveProject(token: String): Result<Project> {
        return projectsRepository.getActiveProject(token)
    }
}
