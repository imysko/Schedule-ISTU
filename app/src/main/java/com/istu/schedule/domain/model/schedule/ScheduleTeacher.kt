package com.istu.schedule.domain.model.schedule

data class ScheduleTeacher(
    val scheduleId: Int,
    val schedule: Schedule,
    val teacherId: Int,
    val teacher: Teacher
)
