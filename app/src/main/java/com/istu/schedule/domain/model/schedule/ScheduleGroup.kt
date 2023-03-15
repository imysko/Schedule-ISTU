package com.istu.schedule.domain.model.schedule

data class ScheduleGroup(
    val scheduleId: Int,
    val schedule: Schedule,
    val groupId: Int,
    val group: Group
)
