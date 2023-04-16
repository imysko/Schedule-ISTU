package com.istu.schedule.data.enums

import com.google.gson.annotations.SerializedName

enum class LessonType {
    @SerializedName("0")
    UNKNOWN,
    @SerializedName("1")
    LECTURE,
    @SerializedName("2")
    PRACTICAL,
    @SerializedName("3")
    LABORATORY_WORK,
    @SerializedName("4")
    PROJECT
}