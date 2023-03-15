package com.istu.schedule.domain.model.schedule

data class LessonTime(
    val lessonId: Int,
    val lessonNumber: String,
    val begtime: String,
    val endtime: String
)
