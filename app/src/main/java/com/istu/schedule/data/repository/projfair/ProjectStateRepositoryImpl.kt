package com.istu.schedule.data.repository.projfair

import com.istu.schedule.data.model.RequestException
import com.istu.schedule.data.service.projfair.ProjectStateService
import com.istu.schedule.domain.model.projfair.ProjectState
import com.istu.schedule.domain.repository.projfair.ProjectStateRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class ProjectStateRepositoryImpl @Inject constructor(
    private val projectStateService: ProjectStateService,
) : ProjectStateRepository {

    private val cachedList: MutableList<ProjectState> = mutableListOf()

    override suspend fun getProjectStatesList(): Result<List<ProjectState>> {
        val apiResponse = projectStateService.getProjectStatesList().body()
        if (apiResponse != null) {
            cachedList.addAll(apiResponse)
            return Result.success(apiResponse)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!",
            ),
        )
    }

    override suspend fun getProjectState(id: Int): Result<ProjectState> {
        return cachedList.find { it.id == id }?.let { projectState ->
            Result.success(projectState)
        } ?: Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!",
            ),
        )
    }
}
