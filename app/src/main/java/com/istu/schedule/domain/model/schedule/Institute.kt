package com.istu.schedule.domain.model.schedule

data class Institute(
    val instituteId: Int,
    val instituteTitle: String?,
    val courses: List<Course>?
)
