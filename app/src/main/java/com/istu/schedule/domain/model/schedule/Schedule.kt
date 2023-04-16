package com.istu.schedule.domain.model.schedule

import com.istu.schedule.data.enums.LessonType

data class Schedule(
    val scheduleId: Int,
    val groupsVerbose: String,
    val scheduleGroups: List<ScheduleGroup>,
    val teachersVerbose: String,
    val scheduleTeachers: List<ScheduleTeacher>,
    val classroomId: Int,
    val classroom: Classroom,
    val classroomVerbose: String,
    val disciplineId: Int,
    val discipline: Discipline?,
    val otherDisciplineId: Int,
    val otherDiscipline: OtherDiscipline?,
    val disciplineVerbose: String,
    val lessonId: Int,
    val lessonTime: LessonTime,
    val subgroup: Int,
    val lessonType: LessonType,
    val scheduleType: String,
    val date: String
)
