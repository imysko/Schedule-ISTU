package com.istu.schedule.data.mappers.api.schedule

import com.istu.schedule.data.api.entities.schedule.TeacherResponse
import com.istu.schedule.domain.entities.schedule.Teacher

fun TeacherResponse.mapToDomain() : Teacher = this.let { from ->
    return Teacher(
        teacherId = from.teacherId,
        fullName = from.fullName,
        shortname = from.shortname,
    )
}
