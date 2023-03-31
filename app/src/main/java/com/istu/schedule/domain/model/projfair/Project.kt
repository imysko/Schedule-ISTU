package com.istu.schedule.domain.model.projfair

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Project(
    val id: Int,
    val prevProjectId: Int?,
    val title: String,
    val places: Int,
    val goal: String,
    val description: String,
    val difficulty: Int,
    @SerializedName("date_start")
    val dateStart: Date,
    @SerializedName("date_end")
    val dateEnd: String,
    val requirements: String,
    @SerializedName("additional_inf")
    val additionalInfo: String,
    @SerializedName("product_result")
    val productResult: String,
    @SerializedName("study_result")
    val studyResult: String,
    val supervisor: List<Supervisor>,
    val projectSupervisors: List<ProjectSupervisor>,
    val skills: List<Skill>,
    val specialities: List<Speciality>,
    @SerializedName("project_specialities")
    val projectSpecialities: List<ProjectSpeciality>,
    val supervisorsNames: String,
    val state: ProjectState,
    val department: Department,
    val type: ProjectType,
    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("updated_at")
    val updatedAt: Date,
)
