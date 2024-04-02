package com.istu.schedule.data.api.entities.schedule

import com.google.gson.annotations.SerializedName

data class QueryResponse(
    @SerializedName("queryId")
    val queryId: Int,
    @SerializedName("description")
    val description: String
)
