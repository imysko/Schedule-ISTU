package com.istu.schedule.domain.model.projfair

data class Speciality(
    val id: Int,
    val name: String,
    val institute: Institute,
    val department: Department
)
