package me.progneo.projfair.domain.usecase

import javax.inject.Inject
import me.progneo.projfair.domain.model.ProjectState
import me.progneo.projfair.domain.repository.ProjectStateRepository

class GetProjectStateUseCase @Inject constructor(
    private val projectStateRepository: ProjectStateRepository
) {

    suspend operator fun invoke(id: Int): Result<ProjectState> {
        return projectStateRepository.getProjectState(id)
    }
}
