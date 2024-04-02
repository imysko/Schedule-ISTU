package com.istu.schedule.data.api.entities.schedule

import com.google.gson.annotations.SerializedName

data class CourseResponse(
    @SerializedName("courseNumber")
    val courseNumber: Int,
    @SerializedName("groups")
    val groups: List<GroupResponse>?
)
