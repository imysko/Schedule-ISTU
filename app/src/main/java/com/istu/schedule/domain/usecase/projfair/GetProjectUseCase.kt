package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.domain.entities.projfair.Project
import com.istu.schedule.domain.repository.projfair.ProjectsRepository
import javax.inject.Inject

class GetProjectUseCase @Inject constructor(
    private val projectsRepository: ProjectsRepository,
) {

    suspend fun getProject(id: Int): Result<Project> {
        return projectsRepository.getProject(id)
    }
}
