package me.progneo.moodle.domain.entities

import com.google.gson.annotations.SerializedName

data class NotificationsCount(
    @SerializedName("unread_notifications_count")
    val unreadNotificationsCount: Int
)
