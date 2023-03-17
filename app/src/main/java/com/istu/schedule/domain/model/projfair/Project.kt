package com.istu.schedule.domain.model.projfair

data class Project(
    val id: Int,
    val prevProjectId: Int?,
    val title: String,
    val places: String,
    val goal: String,
    val description: String,
    val difficulty: Int,
    val date_start: String,
    val date_end: String,
    val requirements: String,
    val additional_inf: String,
    val product_result: String,
    val study_result: String,
    val supervisor: List<Supervisor>,
    val projectSupervisors: List<ProjectSupervisor>,
    val skills: List<Skill>,
    val specialities: List<Speciality>,
    val project_specialities: List<ProjectSpeciality>,
    val supervisorsNames: String,
    val state: ProjectState,
    val department: Department,
    val type: ProjectType,
    val created_at: String,
    val updated_at: String
)
