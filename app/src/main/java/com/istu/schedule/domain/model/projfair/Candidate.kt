package com.istu.schedule.domain.model.projfair

data class Candidate(
    val id: Int,
    val fio: String,
    val about: String,
    val email: String,
    val numz: String,
    val phone: String,
    val course: Int,
    val training_group: String
)