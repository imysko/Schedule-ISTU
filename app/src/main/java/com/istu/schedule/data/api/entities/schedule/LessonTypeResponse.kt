package com.istu.schedule.data.api.entities.schedule

import com.google.gson.annotations.SerializedName

enum class LessonTypeResponse {
    @SerializedName("0")
    UNKNOWN,
    @SerializedName("1")
    LECTURE,
    @SerializedName("2")
    PRACTICAL,
    @SerializedName("3")
    LABORATORY_WORK,
    @SerializedName("4")
    CLASS,
    @SerializedName("5")
    CONSULTATION,
    @SerializedName("6")
    EXAM,
    @SerializedName("7")
    PROJECT,
}
