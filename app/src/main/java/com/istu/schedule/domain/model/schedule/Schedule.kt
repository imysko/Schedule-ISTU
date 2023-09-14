package com.istu.schedule.domain.model.schedule

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.istu.schedule.data.enums.LessonType

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
    val scheduleType: String,
    val date: String
)

class SampleScheduleProvider : PreviewParameterProvider<Schedule> {
    override val values = sequenceOf(
        Schedule(
            scheduleId = 0,
            groupsVerbose = "ИСТб-20-3",
            groups = listOf(
                Group(
                    groupId = 0,
                    name = "ИСТб-20-3",
                    course = 3,
                    instituteId = null,
                    institute = null
                )
            ),
            teachersVerbose = "Аршинский В.Л.",
            teachers = listOf(
                Teacher(
                    teacherId = 0,
                    fullName = "Аршинский Вадим Леонидович",
                    shortname = "Аршинский В.Л."
                )
            ),
            classroomId = 0,
            classroom = Classroom(
                classroomId = 0,
                name = "Д-105б"
            ),
            classroomVerbose = "Д-105б",
            disciplineId = 0,
            discipline = null,
            disciplineVerbose = "Разработка мобильных приложений",
            otherDisciplineId = 0,
            otherDiscipline = null,
            queryId = 0,
            query = null,
            lessonId = 0,
            lessonTime = LessonTime(
                lessonId = 0,
                lessonNumber = "4",
                begTime = "13:45",
                endTime = "15:15"
            ),
            subgroup = 1,
            lessonType = LessonType.LECTURE,
            scheduleType = "",
            date = "2023-03-31"
        )
    )
}
