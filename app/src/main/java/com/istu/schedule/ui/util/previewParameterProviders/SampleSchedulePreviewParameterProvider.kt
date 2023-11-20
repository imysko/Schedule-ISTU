package com.istu.schedule.ui.util.previewParameterProviders

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.istu.schedule.domain.entities.schedule.Classroom
import com.istu.schedule.domain.entities.schedule.Group
import com.istu.schedule.domain.entities.schedule.LessonTime
import com.istu.schedule.domain.entities.schedule.LessonType
import com.istu.schedule.domain.entities.schedule.Schedule
import com.istu.schedule.domain.entities.schedule.Teacher

class SampleSchedulePreviewParameterProvider : PreviewParameterProvider<Schedule> {

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
                    institute = null,
                    isActive = true,
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
