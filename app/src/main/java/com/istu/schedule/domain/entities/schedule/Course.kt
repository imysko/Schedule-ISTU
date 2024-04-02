package com.istu.schedule.domain.entities.schedule

data class Course(
    val courseNumber: Int,
    val groups: List<Group>?
)
