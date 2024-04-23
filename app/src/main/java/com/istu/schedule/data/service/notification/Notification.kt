package com.istu.schedule.data.service.notification

sealed class Notification {

    data object Projfair : Notification()
    data object Campus : Notification()
}
