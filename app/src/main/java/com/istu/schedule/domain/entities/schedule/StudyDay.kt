package com.istu.schedule.domain.entities.schedule

data class StudyDay(
    val date: String,
    val lessons: List<Lesson>
)
