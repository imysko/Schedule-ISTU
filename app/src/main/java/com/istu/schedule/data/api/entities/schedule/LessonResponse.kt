package com.istu.schedule.data.api.entities.schedule

import com.google.gson.annotations.SerializedName

data class LessonResponse(
    @SerializedName("time")
    val time: LessonTimeResponse,
    @SerializedName("breakTimeAfter")
    val breakTimeAfter: String? = null,
    @SerializedName("schedules")
    val schedules: List<ScheduleResponse>
)
