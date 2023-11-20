package com.istu.schedule.domain.entities.schedule

data class Lesson(
    val time: LessonTime,
    val breakTimeAfter: String? = null,
    val schedules: List<Schedule>
)
