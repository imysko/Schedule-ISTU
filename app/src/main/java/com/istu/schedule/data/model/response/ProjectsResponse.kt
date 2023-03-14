package com.istu.schedule.data.model.response

import com.istu.schedule.domain.model.Project

data class ProjectsResponse(
    val data: List<Project>?,
    val projectCount: Int?
)