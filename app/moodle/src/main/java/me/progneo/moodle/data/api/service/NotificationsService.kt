package me.progneo.moodle.data.api.service

import me.progneo.moodle.domain.entities.NotificationsCount
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface NotificationsService {

    @GET("notifications/{aisId}")
    suspend fun getNotificationsCount(@Path("aisId") aisId: Int): Response<NotificationsCount>
}
