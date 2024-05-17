package me.progneo.moodle.domain.repository

import me.progneo.moodle.domain.entities.NotificationsCount

interface NotificationsRepository {

    suspend fun getNotificationsCount(userId: Int): Result<NotificationsCount>
}
