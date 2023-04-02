package com.istu.schedule.domain.model.schedule

import com.istu.schedule.domain.model.DateOnly

data class StudyDay(
    val date: DateOnly,
    val lessons: List<Lesson>
)
