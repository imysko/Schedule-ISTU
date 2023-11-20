package com.istu.schedule.domain.entities.projfair

data class Speciality(
    val id: Int,
    val name: String,
    val institute: Institute,
    val department: Department
)
