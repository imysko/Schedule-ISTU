package com.istu.schedule.domain.entities.schedule

data class Group(
    val groupId: Int,
    val name: String?,
    val course: Int?,
    val instituteId: Int?,
    val institute: Institute?,
    val isActive: Boolean?,
)
