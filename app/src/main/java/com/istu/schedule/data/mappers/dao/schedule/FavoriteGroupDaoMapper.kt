package com.istu.schedule.data.mappers.dao.schedule

import com.istu.schedule.data.database.entities.schedule.FavoriteGroupDto
import com.istu.schedule.domain.entities.schedule.Group

fun FavoriteGroupDto.mapToDomain(): Group = this.let { from ->
    return Group(
        groupId = from.id,
        name = from.name,
        course = null,
        instituteId = null,
        institute = null,
        isActive = from.isActive
    )
}

fun Group.mapToDao(): FavoriteGroupDto = this.let { from ->
    return FavoriteGroupDto(
        id = from.groupId,
        name = from.name ?: "",
        isActive = from.isActive ?: false
    )
}
