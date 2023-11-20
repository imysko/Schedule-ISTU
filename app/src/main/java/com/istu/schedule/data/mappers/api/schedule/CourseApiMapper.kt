package com.istu.schedule.data.mappers.api.schedule

import com.istu.schedule.data.api.entities.schedule.CourseResponse
import com.istu.schedule.domain.entities.schedule.Course

fun CourseResponse.mapToDomain() : Course = this.let { from ->
    return Course(
        courseNumber = from.courseNumber,
        groups = from.groups?.map { it.mapToDomain() },
    )
}
