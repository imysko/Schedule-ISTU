package com.istu.schedule.util

import com.istu.schedule.domain.entities.Subgroup
import com.istu.schedule.domain.entities.UserStatus

fun String?.toUserStatusEnum(): UserStatus? {
    return try {
        this?.let {
            UserStatus.valueOf(it)
        } ?: UserStatus.UNKNOWN
    } catch (ex: Exception) {
        UserStatus.UNKNOWN
    }
}

fun String?.toSubgroupEnum(): Subgroup? {
    return try {
        this?.let {
            Subgroup.valueOf(it)
        } ?: Subgroup.ALL
    } catch (ex: Exception) {
        Subgroup.ALL
    }
}
