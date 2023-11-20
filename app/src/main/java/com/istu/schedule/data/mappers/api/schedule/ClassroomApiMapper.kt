package com.istu.schedule.data.mappers.api.schedule

import com.istu.schedule.data.api.entities.schedule.ClassroomResponse
import com.istu.schedule.domain.entities.schedule.Classroom

fun ClassroomResponse.mapToDomain() : Classroom = this.let { from ->
    return Classroom(
        classroomId = from.classroomId,
        name = from.name,
    )
}
