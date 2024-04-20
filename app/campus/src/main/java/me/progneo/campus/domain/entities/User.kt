package me.progneo.campus.domain.entities

data class User(
    val id: Int,
    val name: String,
    val lastName: String?,
    val secondName: String?
)
