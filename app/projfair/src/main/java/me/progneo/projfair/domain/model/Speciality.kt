package me.progneo.projfair.domain.model

data class Speciality(
    val id: Int,
    val name: String,
    val institute: Institute,
    val department: Department
)
