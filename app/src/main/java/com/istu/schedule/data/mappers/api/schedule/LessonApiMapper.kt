package com.istu.schedule.data.mappers.api.schedule

import com.istu.schedule.data.api.entities.schedule.LessonResponse
import com.istu.schedule.domain.entities.schedule.Lesson

fun LessonResponse.mapToDomain(): Lesson = this.let { from ->
    return Lesson(
        time = from.time.mapToDomain(),
        breakTimeAfter = from.breakTimeAfter,
        schedules = from.schedules.map { it.mapToDomain() }
    )
}
