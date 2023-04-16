package com.istu.schedule.domain.model.schedule

data class StudyDay(
    val date: String,
    val lessons: List<Lesson>
)
