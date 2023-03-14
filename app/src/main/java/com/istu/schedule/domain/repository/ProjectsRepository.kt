package com.istu.schedule.domain.repository

import com.istu.schedule.domain.model.Project

interface ProjectsRepository {
    suspend fun getProjects(page: Int): Result<List<Project>>
}