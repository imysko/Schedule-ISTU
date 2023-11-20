package com.istu.schedule.data.api.entities.schedule

import com.google.gson.annotations.SerializedName

data class LessonTimeResponse(
    @SerializedName("lessonId")
    val lessonId: Int,
    @SerializedName("lessonNumber")
    val lessonNumber: String,
    @SerializedName("begtime")
    val begTime: String,
    @SerializedName("endtime")
    val endTime: String,
)
