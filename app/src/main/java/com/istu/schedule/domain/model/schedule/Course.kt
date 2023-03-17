package com.istu.schedule.domain.model.schedule

data class Course(
    val courseNumber: Int,
    val groups: List<Group>?
)
