package com.istu.schedule.data.mappers.api.schedule

import com.istu.schedule.data.api.entities.schedule.OtherDisciplineResponse
import com.istu.schedule.domain.entities.schedule.OtherDiscipline

fun OtherDisciplineResponse.mapToDomain() : OtherDiscipline = this.let { from ->
    return OtherDiscipline(
        otherDisciplineId = from.otherDisciplineId,
        disciplineTitle = from.disciplineTitle,
        isOnline = from.isOnline,
        type = from.type,
    )
}
