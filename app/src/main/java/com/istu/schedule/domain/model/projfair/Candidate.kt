package com.istu.schedule.domain.model.projfair

import com.google.gson.annotations.SerializedName

data class Candidate(
    val id: Int,
    val fio: String,
    val about: String,
    val email: String,
    @SerializedName("numz")
    val courseBookNumber: String,
    val phone: String,
    val course: Int,
    @SerializedName("training_group")
    val trainingGroup: String,
    val speciality: Speciality,
)