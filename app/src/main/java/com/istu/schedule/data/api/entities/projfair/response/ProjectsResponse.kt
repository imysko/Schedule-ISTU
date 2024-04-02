package com.istu.schedule.data.api.entities.projfair.response

import me.progneo.projfair.domain.model.Project

data class ProjectsResponse(
    val data: List<Project>?,
    val projectCount: Int?
)
