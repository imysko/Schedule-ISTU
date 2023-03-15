package com.istu.schedule.domain.model.schedule

data class Group(
    val groupId: Int,
    val name: String?,
    val course: Int?,
    val instituteId: Int?,
    val institute: Institute?
)
