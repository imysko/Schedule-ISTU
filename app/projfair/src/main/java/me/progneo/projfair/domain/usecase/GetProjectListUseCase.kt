package me.progneo.projfair.domain.usecase

import javax.inject.Inject
import me.progneo.projfair.domain.model.Project
import me.progneo.projfair.domain.repository.ProjectRepository

class GetProjectListUseCase @Inject constructor(
    private val projectRepository: ProjectRepository
) {

    suspend operator fun invoke(
        token: String = "",
        title: String = "",
        page: Int,
        difficulties: List<Int> = arrayListOf(),
        states: List<Int> = arrayListOf(),
        specialties: List<Int> = arrayListOf(),
        skills: List<Int> = arrayListOf()
    ): Result<List<Project>> {
        return projectRepository.getProjects(
            token = token,
            title = title,
            page = page,
            difficulties = difficulties,
            states = states,
            specialties = specialties,
            skills = skills
        )
    }
}
