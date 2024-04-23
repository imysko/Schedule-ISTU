package me.progneo.projfair.domain.repository

import me.progneo.projfair.domain.model.Project

interface ProjectRepository {
    suspend fun getProjects(
        title: String,
        page: Int,
        difficulties: List<Int>,
        states: List<Int>,
        specialties: List<Int>,
        skills: List<Int>
    ): Result<List<Project>>

    suspend fun getActiveProject(): Result<Project>
    suspend fun getArchiveProjects(): Result<List<Project>>
    suspend fun getProject(id: Int): Result<Project>
}
