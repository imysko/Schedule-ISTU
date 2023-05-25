package com.istu.schedule.domain.model.schedule

import com.google.gson.annotations.SerializedName

data class Teacher(
    val teacherId: Int,
    @SerializedName("fullname")
    val fullName: String,
    val shortname: String,
)
