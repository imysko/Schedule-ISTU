package com.istu.schedule.data.mappers.api.schedule

import com.istu.schedule.data.api.entities.schedule.DisciplineResponse
import com.istu.schedule.domain.entities.schedule.Discipline

fun DisciplineResponse.mapToDomain(): Discipline = this.let { from ->
    return Discipline(
        disciplineId = from.disciplineId,
        title = from.title,
        realTitle = from.realTitle
    )
}
