package com.istu.schedule.data.mappers.api.schedule

import com.istu.schedule.data.api.entities.schedule.InstituteResponse
import com.istu.schedule.domain.entities.schedule.Institute

fun InstituteResponse.mapToDomain() : Institute = this.let { from ->
    return Institute(
        instituteId = from.instituteId,
        instituteTitle = from.instituteTitle,
        courses = from.courses?.map { it.mapToDomain() },
    )
}
