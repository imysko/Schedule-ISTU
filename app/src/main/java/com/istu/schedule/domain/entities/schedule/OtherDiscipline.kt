package com.istu.schedule.domain.entities.schedule

data class OtherDiscipline(
    val otherDisciplineId: Int,
    val disciplineTitle: String,
    val isOnline: Boolean,
    val type: Int,
)
