package com.istu.schedule.domain.entities.projfair

data class ProjectSupervisor(
    val id: Int,
    val roles: List<ProjectSupervisorRole>,
    val supervisor: Supervisor
)
