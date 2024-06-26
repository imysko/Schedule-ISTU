package me.progneo.projfair.domain.model

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
    val dateEnd: Date,
    val requirements: String,
    @SerializedName("additional_inf")
    val additionalInfo: String,
    @SerializedName("product_result")
    val productResult: String,
    @SerializedName("study_result")
    val studyResult: String,
    val customer: String,
    val supervisors: List<ProjectSupervisor>,
    val skills: List<Skill>,
    val specialities: List<Speciality>,
    @SerializedName("project_specialities")
    val projectSpecialities: List<ProjectSpeciality>,
    val state: ProjectState,
    val department: Department,
    val type: ProjectType,
    val participations: List<Participation>,
    @SerializedName("created_at")
    val createdAt: Date,
    @SerializedName("updated_at")
    val updatedAt: Date
)
