package com.istu.schedule.domain.model.projfair

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Participation(
    val id: Int,
    val priority: Int,
    @SerializedName("project_id")
    val projectId: Int,
    @SerializedName("state_id")
    val stateId: Int,
    @SerializedName("candidate_id")
    val candidateId: Int,
    @SerializedName("crated_at")
    val cratedAt: Date,
    @SerializedName("updated_at")
    val updatedAt: Date,
)
