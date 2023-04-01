package com.istu.schedule.domain.model.schedule

import com.google.gson.annotations.SerializedName

data class LessonTime(
    val lessonId: Int,
    val lessonNumber: String,
    @SerializedName("begtime")
    val begTime: String,
    @SerializedName("endtime")
    val endTime: String,
)
