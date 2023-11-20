package com.istu.schedule.data.mappers.api.schedule

import com.istu.schedule.data.api.entities.schedule.LessonTypeResponse
import com.istu.schedule.domain.entities.schedule.LessonType

fun LessonTypeResponse.mapToDomain() : LessonType = this.let { from ->
    return when (from) {
        LessonTypeResponse.UNKNOWN -> LessonType.UNKNOWN
        LessonTypeResponse.LECTURE -> LessonType.LECTURE
        LessonTypeResponse.PRACTICAL -> LessonType.PRACTICAL
        LessonTypeResponse.LABORATORY_WORK -> LessonType.LABORATORY_WORK
        LessonTypeResponse.CLASS -> LessonType.CLASS
        LessonTypeResponse.CONSULTATION -> LessonType.CONSULTATION
        LessonTypeResponse.EXAM -> LessonType.EXAM
        LessonTypeResponse.PROJECT -> LessonType.PROJECT
    }
}
