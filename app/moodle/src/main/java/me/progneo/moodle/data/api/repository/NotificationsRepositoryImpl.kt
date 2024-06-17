package me.progneo.moodle.data.api.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import me.progneo.moodle.data.api.service.NotificationsService
import me.progneo.moodle.data.exception.RequestException
import me.progneo.moodle.domain.entities.NotificationsCount
import me.progneo.moodle.domain.repository.NotificationsRepository

internal class NotificationsRepositoryImpl @Inject constructor(
    private val notificationsService: NotificationsService
) : NotificationsRepository {

    override suspend fun getNotificationsCount(userId: Int): Result<NotificationsCount> {
        val apiResponse = notificationsService.getNotificationsCount(userId)

        if (apiResponse.code() == HttpURLConnection.HTTP_OK) {
            apiResponse.body()?.let { response ->
                return Result.success(response)
            }
        }

        return Result.failure(
            RequestException(
                code = apiResponse.code(),
                message = apiResponse.message()
            )
        )
    }
}
