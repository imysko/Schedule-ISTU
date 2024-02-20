package me.progneo.projfair.domain.repository

import me.progneo.projfair.domain.model.Project

interface ProjectRepository {
    suspend fun getProjects(
        token: String,
        title: String,
        page: Int,
        difficulties: List<Int>,
        states: List<Int>,
        specialties: List<Int>,
        skills: List<Int>
    ): Result<List<Project>>

    suspend fun getActiveProject(token: String): Result<Project>
    suspend fun getArchiveProjects(token: String): Result<List<Project>>
    suspend fun getProject(id: Int): Result<Project>
}
