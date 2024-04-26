package me.progneo.projfair.data.api.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import me.progneo.projfair.data.api.service.ProjectsService
import me.progneo.projfair.data.exception.RequestException
import me.progneo.projfair.domain.model.Project
import me.progneo.projfair.domain.repository.ProjectRepository

internal class ProjectRepositoryImpl @Inject constructor(
    private val projectsService: ProjectsService
) : ProjectRepository {

    private val cachedList: MutableList<Project> = mutableListOf()

    override suspend fun getProjects(
        title: String,
        page: Int,
        difficulties: List<Int>,
        states: List<Int>,
        specialties: List<Int>,
        skills: List<Int>
    ): Result<List<Project>> {
        val apiResponse = projectsService.getProjectList(
            title = title,
            page = page,
            difficulties = difficulties.toString().replace(" ", ""),
            states = states.toString().replace(" ", ""),
            specialties = specialties.toString().replace(" ", ""),
            skills = skills.toString().replace(" ", "")
        ).body()
        if (apiResponse?.data != null) {
            val newsList = apiResponse.data
            cachedList.let { list ->
                list.addAll(newsList)
                list.distinctBy { it.id }
            }
            return Result.success(newsList)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }

    override suspend fun getActiveProject(): Result<Project> {
        val apiResponse = projectsService.getActiveProject().body()
        if (apiResponse != null) {
            return Result.success(apiResponse)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }

    override suspend fun getArchiveProjects(): Result<List<Project>> {
        val apiResponse = projectsService.getArchiveProjectList().body()
        if (apiResponse != null) {
            return Result.success(apiResponse)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
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
                    message = "An error occurred!"
                )
            )
        }
    }
}
