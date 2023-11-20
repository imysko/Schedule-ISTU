package com.istu.schedule.data.api.entities.projfair.response

import com.istu.schedule.domain.entities.projfair.Project

data class ProjectsResponse(
    val data: List<Project>?,
    val projectCount: Int?,
)
