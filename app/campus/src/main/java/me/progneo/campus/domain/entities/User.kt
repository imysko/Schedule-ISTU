package me.progneo.campus.domain.entities

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("NAME")
    val name: String,
    @SerializedName("LAST_NAME")
    val lastName: String?,
    @SerializedName("SECOND_NAME")
    val secondName: String?
)
