package com.istu.schedule.data.mappers.api.schedule

import com.istu.schedule.data.api.entities.schedule.StudyDayResponse
import com.istu.schedule.domain.entities.schedule.StudyDay

fun StudyDayResponse.mapToDomain() : StudyDay = this.let { from ->
    return StudyDay(
        date = from.date,
        lessons = from.lessons.map { it.mapToDomain() },
    )
}
