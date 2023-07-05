package com.istu.schedule.util

import com.istu.schedule.data.enums.UserStatus

fun String?.toUserStatusEnum(): UserStatus? {
    return try {
        this?.let {
            UserStatus.valueOf(it)
        } ?: UserStatus.UNKNOWN
    } catch (ex: Exception) {
        UserStatus.UNKNOWN
    }
}
