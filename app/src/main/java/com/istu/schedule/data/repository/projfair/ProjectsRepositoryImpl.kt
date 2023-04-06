package com.istu.schedule.data.repository.projfair

import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.service.projfair.ProjectsService
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.domain.repository.projfair.ProjectsRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class ProjectsRepositoryImpl @Inject constructor(
    private val projectsService: ProjectsService,
) : ProjectsRepository {

    private val cachedList: MutableList<Project> = mutableListOf()

    override suspend fun getProjects(
        token: String,
        title: String,
        page: Int,
        difficulties: List<Int>,
        states: List<Int>,
        specialties: List<Int>,
        skills: List<Int>,
    ): Result<List<Project>> {
        val apiResponse = projectsService.getProjects(
            token = token,
            title = title,
            page = page,
            difficulties = difficulties,
            states = states,
            specialties = specialties,
            skills = skills,
        ).body()
        if (apiResponse?.data != null) {
            val newsList = apiResponse.data
            cachedList.addAll(newsList)
            return Result.success(newsList)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!",
            ),
        )
    }

    override suspend fun getActiveProject(token: String): Result<Project> {
        val apiResponse = projectsService.getActiveProject(token).body()
        if (apiResponse != null) {
            return Result.success(apiResponse)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!",
            ),
        )
    }

    override suspend fun getArchiveProjects(token: String): Result<List<Project>> {
        val apiResponse = projectsService.getArchiveProjects(token).body()
        if (apiResponse != null) {
            return Result.success(apiResponse)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!",
            ),
        )
    }

    override suspend fun getProject(id: Int): Result<Project> {
        return cachedList.find { it.id == id }?.let { project ->
            Result.success(project)
        } ?: run {
            val apiResponse = projectsService.getProject(id).body()
            if (apiResponse != null) {
                return Result.success(apiResponse)
            }
            return Result.failure(
                RequestException(
                    code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                    message = "An error occurred!",
                ),
            )
        }
    }
}
