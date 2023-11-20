package com.istu.schedule.data.api.entities.schedule

import com.google.gson.annotations.SerializedName

data class DisciplineResponse(
    @SerializedName("disciplineId")
    val disciplineId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("realTitle")
    val realTitle: String,
)
