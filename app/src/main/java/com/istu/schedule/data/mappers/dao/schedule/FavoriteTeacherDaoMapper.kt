package com.istu.schedule.data.mappers.dao.schedule

import com.istu.schedule.data.database.entities.schedule.FavoriteTeacherDto
import com.istu.schedule.domain.entities.schedule.Teacher

fun FavoriteTeacherDto.mapToDomain() : Teacher = this.let { from ->
    return Teacher(
        teacherId = from.id,
        fullName = from.fullName,
        shortname = from.shortName,
    )
}

fun Teacher.mapToDao() : FavoriteTeacherDto = this.let { from ->
    return FavoriteTeacherDto(
        id = from.teacherId,
        fullName = from.fullName,
        shortName = from.shortname,
    )
}
