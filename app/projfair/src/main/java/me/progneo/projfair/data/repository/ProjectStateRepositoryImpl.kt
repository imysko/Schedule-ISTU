package me.progneo.projfair.data.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import me.progneo.projfair.data.exception.RequestException
import me.progneo.projfair.data.service.ProjectStateService
import me.progneo.projfair.domain.model.ProjectState
import me.progneo.projfair.domain.repository.ProjectStateRepository

internal class ProjectStateRepositoryImpl @Inject constructor(
    private val projectStateService: ProjectStateService
) : ProjectStateRepository {

    private val cachedList: MutableList<ProjectState> = mutableListOf()

    override suspend fun getProjectStatesList(): Result<List<ProjectState>> {
        val apiResponse = projectStateService.getProjectStateList().body()
        if (apiResponse != null) {
            cachedList.addAll(apiResponse)
            return Result.success(apiResponse)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }

    override suspend fun getProjectState(id: Int): Result<ProjectState> {
        return cachedList.find { it.id == id }?.let { projectState ->
            Result.success(projectState)
        } ?: Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }
}
