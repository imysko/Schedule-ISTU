package com.istu.schedule.domain.entities.schedule

data class Institute(
    val instituteId: Int,
    val instituteTitle: String?,
    val courses: List<Course>?,
)
