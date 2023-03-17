package com.istu.schedule.domain.model.projfair

data class ProjectSupervisor(
    val id: Int,
    val priority: Int,
    val role: ProjectSupervisorRole,
    val supervisor: Supervisor
)