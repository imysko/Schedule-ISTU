package me.progneo.projfair.domain.usecase

import javax.inject.Inject
import me.progneo.projfair.domain.model.Project
import me.progneo.projfair.domain.repository.ProjectRepository

class GetProjectUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {

    suspend operator fun invoke(id: Int): Result<Project> {
        return projectRepository.getProject(id)
    }
}
