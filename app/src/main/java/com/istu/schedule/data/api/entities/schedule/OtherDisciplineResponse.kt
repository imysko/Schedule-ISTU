package com.istu.schedule.data.api.entities.schedule

import com.google.gson.annotations.SerializedName

data class OtherDisciplineResponse(
    @SerializedName("otherDisciplineId")
    val otherDisciplineId: Int,
    @SerializedName("disciplineTitle")
    val disciplineTitle: String,
    @SerializedName("isOnline")
    val isOnline: Boolean,
    @SerializedName("type")
    val type: Int
)
