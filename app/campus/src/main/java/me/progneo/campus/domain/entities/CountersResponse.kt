package me.progneo.campus.domain.entities

import com.google.gson.annotations.SerializedName

data class CountersResponse(
    @SerializedName("TYPE")
    val counters: Counters
)
