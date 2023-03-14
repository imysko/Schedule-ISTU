package com.istu.schedule.domain.model

data class ProjectSupervisor(
    val id: Int,
    val priority: Int,
    val role: ProjectSupervisorRole,
    val supervisor: Supervisor
)