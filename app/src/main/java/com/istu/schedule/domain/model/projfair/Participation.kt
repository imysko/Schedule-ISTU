package com.istu.schedule.domain.model.projfair

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Participation(
    val id: Int,
    val priority: Int,
    @SerializedName("project_id")
    val projectId: Int,
    @SerializedName("candidate_id")
    val candidateId: Int,
    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("updated_at")
    val updatedAt: Date,
    val candidate: Candidate?,
    val state: State,
    val project: Project?
)
