package com.istu.schedule.domain.entities.schedule

data class LessonTime(
    val lessonId: Int,
    val lessonNumber: String,
    val begTime: String,
    val endTime: String,
)
