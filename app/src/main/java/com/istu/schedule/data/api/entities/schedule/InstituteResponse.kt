package com.istu.schedule.data.api.entities.schedule

import com.google.gson.annotations.SerializedName

data class InstituteResponse(
    @SerializedName("instituteId")
    val instituteId: Int,
    @SerializedName("instituteTitle")
    val instituteTitle: String?,
    @SerializedName("courses")
    val courses: List<CourseResponse>?
)
