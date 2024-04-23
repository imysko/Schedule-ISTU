package com.istu.schedule.data.service.notification

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface NotificationManager {

    fun addNotification(notification: Notification)

    fun removeNotification(notification: Notification)

    fun getNotificationList(): Flow<List<Notification>>
}

internal class NotificationManagerImpl @Inject constructor() : NotificationManager {

    private val _notificationList = MutableStateFlow<List<Notification>>(listOf())

    override fun addNotification(notification: Notification) {
        val currentList = _notificationList.value
        when (notification) {
            Notification.Campus -> {
                if (!currentList.any { it is Notification.Campus }) {
                    _notificationList.tryEmit(currentList + notification)
                }
            }

            Notification.Projfair -> {
                if (!currentList.any { it is Notification.Projfair }) {
                    _notificationList.tryEmit(currentList + notification)
                }
            }
        }
    }

    override fun removeNotification(notification: Notification) {
        val notificationList = _notificationList.value.toMutableList()
        when (notification) {
            Notification.Campus -> {
                notificationList.removeIf { it is Notification.Campus }
            }

            Notification.Projfair -> {
                notificationList.removeIf { it is Notification.Projfair }
            }
        }
        _notificationList.tryEmit(notificationList)
    }

    override fun getNotificationList(): Flow<List<Notification>> = _notificationList
}
