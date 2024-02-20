package me.progneo.projfair.domain.model

data class ProjectSupervisor(
    val id: Int,
    val roles: List<ProjectSupervisorRole>,
    val supervisor: Supervisor
)
