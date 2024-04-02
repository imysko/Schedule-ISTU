package com.istu.schedule.data.api.entities.schedule

import com.google.gson.annotations.SerializedName

data class GroupResponse(
    @SerializedName("groupId")
    val groupId: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("course")
    val course: Int?,
    @SerializedName("instituteId")
    val instituteId: Int?,
    @SerializedName("institute")
    val institute: InstituteResponse?,
    @SerializedName("isActive")
    val isActive: Boolean?
)
