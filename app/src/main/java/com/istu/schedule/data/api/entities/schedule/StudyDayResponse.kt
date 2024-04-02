package com.istu.schedule.data.api.entities.schedule

import com.google.gson.annotations.SerializedName

data class StudyDayResponse(
    @SerializedName("date")
    val date: String,
    @SerializedName("lessons")
    val lessons: List<LessonResponse>
)
