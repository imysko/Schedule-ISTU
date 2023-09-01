package com.istu.schedule.domain.model.projfair

data class ProjectSupervisor(
    val id: Int,
    val roles: List<ProjectSupervisorRole>,
    val supervisor: Supervisor
)
