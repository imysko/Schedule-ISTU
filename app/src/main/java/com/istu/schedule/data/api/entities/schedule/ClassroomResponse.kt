package com.istu.schedule.data.api.entities.schedule

import com.google.gson.annotations.SerializedName

data class ClassroomResponse(
    @SerializedName("classroomId")
    val classroomId: Int,
    @SerializedName("name")
    val name: String
)
