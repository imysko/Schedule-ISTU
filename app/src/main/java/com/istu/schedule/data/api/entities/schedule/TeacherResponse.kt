package com.istu.schedule.data.api.entities.schedule

import com.google.gson.annotations.SerializedName

data class TeacherResponse(
    @SerializedName("teacherId")
    val teacherId: Int,
    @SerializedName("fullname")
    val fullName: String,
    @SerializedName("shortname")
    val shortname: String
)
