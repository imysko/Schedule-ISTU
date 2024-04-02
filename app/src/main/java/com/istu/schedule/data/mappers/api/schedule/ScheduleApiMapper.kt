package com.istu.schedule.data.mappers.api.schedule

import com.istu.schedule.data.api.entities.schedule.ScheduleResponse
import com.istu.schedule.domain.entities.schedule.Schedule

fun ScheduleResponse.mapToDomain(): Schedule = this.let { from ->
    return Schedule(
        scheduleId = from.scheduleId,
        groupsVerbose = from.groupsVerbose,
        groups = from.groups?.map { it.mapToDomain() },
        teachersVerbose = from.teachersVerbose,
        teachers = from.teachers?.map { it.mapToDomain() },
        classroomVerbose = from.classroomVerbose,
        classroomId = from.classroomId,
        classroom = from.classroom?.mapToDomain(),
        disciplineVerbose = from.disciplineVerbose,
        disciplineId = from.disciplineId,
        discipline = from.discipline?.mapToDomain(),
        otherDisciplineId = from.otherDisciplineId,
        otherDiscipline = from.otherDiscipline?.mapToDomain(),
        queryId = from.queryId,
        query = from.query?.mapToDomain(),
        lessonId = from.lessonId,
        lessonTime = from.lessonTime.mapToDomain(),
        subgroup = from.subgroup,
        lessonType = from.lessonType?.mapToDomain(),
        scheduleType = from.scheduleType,
        date = from.date
    )
}
