package com.istu.schedule.data.mappers.dao.schedule

import com.istu.schedule.data.database.entities.schedule.FavoriteClassroomDto
import com.istu.schedule.domain.entities.schedule.Classroom

fun FavoriteClassroomDto.mapToDomain(): Classroom = this.let { from ->
    return Classroom(
        classroomId = from.id,
        name = from.name
    )
}

fun Classroom.mapToDao(): FavoriteClassroomDto = this.let { from ->
    return FavoriteClassroomDto(
        id = from.classroomId,
        name = from.name
    )
}
