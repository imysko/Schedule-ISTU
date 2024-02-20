package me.progneo.projfair.domain.usecase

import javax.inject.Inject
import me.progneo.projfair.domain.model.Project
import me.progneo.projfair.domain.repository.ProjectRepository

class GetActiveProjectUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {

    suspend operator fun invoke(token: String): Result<Project> {
        return projectRepository.getActiveProject(token)
    }
}
