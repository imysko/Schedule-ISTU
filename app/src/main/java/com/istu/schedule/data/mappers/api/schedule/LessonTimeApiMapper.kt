package com.istu.schedule.data.mappers.api.schedule

import com.istu.schedule.data.api.entities.schedule.LessonTimeResponse
import com.istu.schedule.domain.entities.schedule.LessonTime

fun LessonTimeResponse.mapToDomain(): LessonTime = this.let { from ->
    return LessonTime(
        lessonId = from.lessonId,
        lessonNumber = from.lessonNumber,
        begTime = from.begTime,
        endTime = from.endTime
    )
}
