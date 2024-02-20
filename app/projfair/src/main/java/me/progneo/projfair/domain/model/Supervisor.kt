package me.progneo.projfair.domain.model

data class Supervisor(
    val id: Int,
    val fio: String,
    val email: String,
    val about: String,
    val position: String,
    val department: Department
)
