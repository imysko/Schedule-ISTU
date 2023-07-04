package com.istu.schedule.domain.usecase.projfair

import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.domain.repository.projfair.ProjectsRepository
import javax.inject.Inject

class GetProjectsListUseCase @Inject constructor(
    private val projectsRepository: ProjectsRepository
) {

    suspend fun getProjectsList(
        token: String = "",
        title: String = "",
        page: Int,
        difficulties: List<Int> = arrayListOf(),
        states: List<Int> = arrayListOf(),
        specialties: List<Int> = arrayListOf(),
        skills: List<Int> = arrayListOf()
    ): Result<List<Project>> {
        return projectsRepository.getProjects(
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
