package com.istu.schedule.data.repository

import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.service.ProjectsService
import com.istu.schedule.domain.model.Project
import com.istu.schedule.domain.repository.ProjectsRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class ProjectsRepositoryImpl @Inject constructor(
    private val projectsService: ProjectsService
) : ProjectsRepository {

    private val cachedList: MutableList<Project> = mutableListOf()

    override suspend fun getProjects(page: Int): Result<List<Project>> {
        val apiResponse = projectsService.getProjects(page).body()
        if (apiResponse?.data != null) {
            val newsList = apiResponse.data
            cachedList.addAll(newsList)
            return Result.success(newsList)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }
}