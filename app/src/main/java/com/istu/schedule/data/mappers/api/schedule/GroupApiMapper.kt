package com.istu.schedule.data.mappers.api.schedule

import com.istu.schedule.data.api.entities.schedule.GroupResponse
import com.istu.schedule.domain.entities.schedule.Group

fun GroupResponse.mapToDomain(): Group = this.let { from ->
    return Group(
        groupId = from.groupId,
        name = from.name,
        course = from.course,
        instituteId = from.instituteId,
        institute = from.institute?.mapToDomain(),
        isActive = from.isActive
    )
}
