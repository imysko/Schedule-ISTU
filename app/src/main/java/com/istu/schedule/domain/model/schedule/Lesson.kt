package com.istu.schedule.domain.model.schedule

data class Lesson(
    val time: LessonTime,
    val schedules: List<Schedule>
)
