package me.progneo.projfair.domain.usecase

import javax.inject.Inject
import me.progneo.projfair.domain.model.Project
import me.progneo.projfair.domain.repository.ProjectRepository

class GetArchiveProjectsListUseCase @Inject constructor(
    private val projectsRepository: ProjectRepository
) {

    suspend operator fun invoke(token: String): Result<List<Project>> {
        return projectsRepository.getArchiveProjects(token)
    }
}
