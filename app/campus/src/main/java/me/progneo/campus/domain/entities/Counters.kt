package me.progneo.campus.domain.entities

import com.google.gson.annotations.SerializedName

data class Counters(
    @SerializedName("ALL")
    val all: Int,
    @SerializedName("NOTIFY")
    val notify: Int,
    @SerializedName("DIALOG")
    val dialog: Int,
    @SerializedName("CHAT")
    val chat: Int,
    @SerializedName("LINES")
    val lines: Int
)
