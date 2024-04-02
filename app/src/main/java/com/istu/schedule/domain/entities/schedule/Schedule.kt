package com.istu.schedule.domain.entities.schedule

data class Schedule(
    val scheduleId: Int,
    val groupsVerbose: String,
    val groups: List<Group>?,
    val teachersVerbose: String,
    val teachers: List<Teacher>?,
    val classroomId: Int?,
    val classroom: Classroom?,
    val classroomVerbose: String,
    val disciplineId: Int,
    val discipline: Discipline?,
    val otherDisciplineId: Int,
    val otherDiscipline: OtherDiscipline?,
    val queryId: Int?,
    val query: Query?,
    val disciplineVerbose: String,
    val lessonId: Int,
    val lessonTime: LessonTime,
    val subgroup: Int,
    val lessonType: LessonType?,
    val scheduleType: String?,
    val date: String
)
