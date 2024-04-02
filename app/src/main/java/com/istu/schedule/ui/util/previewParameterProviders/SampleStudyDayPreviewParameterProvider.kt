package com.istu.schedule.ui.util.previewParameterProviders

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.istu.schedule.domain.entities.schedule.Classroom
import com.istu.schedule.domain.entities.schedule.Group
import com.istu.schedule.domain.entities.schedule.Lesson
import com.istu.schedule.domain.entities.schedule.LessonTime
import com.istu.schedule.domain.entities.schedule.LessonType
import com.istu.schedule.domain.entities.schedule.Schedule
import com.istu.schedule.domain.entities.schedule.StudyDay
import com.istu.schedule.domain.entities.schedule.Teacher

class SampleStudyDayPreviewParameterProvider : PreviewParameterProvider<StudyDay> {

    override val values = sequenceOf(
        StudyDay(
            date = "2023-11-08",
            lessons = listOf(
                Lesson(
                    time = LessonTime(
                        lessonId = 3,
                        lessonNumber = "3",
                        begTime = "11:45",
                        endTime = "13:15"
                    ),
                    breakTimeAfter = "00:30:00",
                    schedules = listOf(
                        Schedule(
                            scheduleId = 0,
                            groupsVerbose = "ИСТб-20",
                            groups = listOf(
                                Group(
                                    groupId = 0,
                                    name = "ИСТб-20-1",
                                    course = 3,
                                    instituteId = null,
                                    institute = null,
                                    isActive = true
                                ),
                                Group(
                                    groupId = 0,
                                    name = "ИСТб-20-2",
                                    course = 3,
                                    instituteId = null,
                                    institute = null,
                                    isActive = true
                                ),
                                Group(
                                    groupId = 0,
                                    name = "ИСТб-20-3",
                                    course = 3,
                                    instituteId = null,
                                    institute = null,
                                    isActive = true
                                )
                            ),
                            teachersVerbose = "Мамедов Э.Ф.",
                            teachers = listOf(
                                Teacher(
                                    teacherId = 0,
                                    fullName = "Мамедов Эльшан Фахраддинович",
                                    shortname = "Мамедов Э.Ф."
                                )
                            ),
                            classroomId = 0,
                            classroom = Classroom(
                                classroomId = 0,
                                name = "К-302"
                            ),
                            classroomVerbose = "К-302",
                            disciplineId = 0,
                            discipline = null,
                            disciplineVerbose = "Основы информационной безопасности",
                            otherDisciplineId = 0,
                            otherDiscipline = null,
                            queryId = 0,
                            query = null,
                            lessonId = 3,
                            lessonTime = LessonTime(
                                lessonId = 3,
                                lessonNumber = "3",
                                begTime = "11:45",
                                endTime = "13:15"
                            ),
                            subgroup = 0,
                            lessonType = LessonType.LECTURE,
                            scheduleType = "",
                            date = "2023-11-08"
                        )
                    )
                ),
                Lesson(
                    time = LessonTime(
                        lessonId = 4,
                        lessonNumber = "4",
                        begTime = "13:45",
                        endTime = "15:15"
                    ),
                    breakTimeAfter = null,
                    schedules = listOf(
                        Schedule(
                            scheduleId = 0,
                            groupsVerbose = "ИСТб-20",
                            groups = listOf(
                                Group(
                                    groupId = 0,
                                    name = "ИСТб-20-1",
                                    course = 3,
                                    instituteId = null,
                                    institute = null,
                                    isActive = true
                                ),
                                Group(
                                    groupId = 0,
                                    name = "ИСТб-20-2",
                                    course = 3,
                                    instituteId = null,
                                    institute = null,
                                    isActive = true
                                ),
                                Group(
                                    groupId = 0,
                                    name = "ИСТб-20-3",
                                    course = 3,
                                    instituteId = null,
                                    institute = null,
                                    isActive = true
                                )
                            ),
                            teachersVerbose = "Маланова Т.В.",
                            teachers = listOf(
                                Teacher(
                                    teacherId = 0,
                                    fullName = "Маланова Татьяна Валерьевна",
                                    shortname = "Маланова Т.В."
                                )
                            ),
                            classroomId = 0,
                            classroom = Classroom(
                                classroomId = 0,
                                name = "К-302"
                            ),
                            classroomVerbose = "К-302",
                            disciplineId = 0,
                            discipline = null,
                            disciplineVerbose = "Теория информационных процессов и систем",
                            otherDisciplineId = 0,
                            otherDiscipline = null,
                            queryId = 0,
                            query = null,
                            lessonId = 3,
                            lessonTime = LessonTime(
                                lessonId = 4,
                                lessonNumber = "4",
                                begTime = "13:45",
                                endTime = "15:15"
                            ),
                            subgroup = 0,
                            lessonType = LessonType.LECTURE,
                            scheduleType = "",
                            date = "2023-11-08"
                        )
                    )
                ),
                Lesson(
                    time = LessonTime(
                        lessonId = 5,
                        lessonNumber = "5",
                        begTime = "15:30",
                        endTime = "17:00"
                    ),
                    breakTimeAfter = null,
                    schedules = listOf(
                        Schedule(
                            scheduleId = 0,
                            groupsVerbose = "ИСТб-20",
                            groups = listOf(
                                Group(
                                    groupId = 0,
                                    name = "ИСТб-20-1",
                                    course = 3,
                                    instituteId = null,
                                    institute = null,
                                    isActive = true
                                ),
                                Group(
                                    groupId = 0,
                                    name = "ИСТб-20-2",
                                    course = 3,
                                    instituteId = null,
                                    institute = null,
                                    isActive = true
                                ),
                                Group(
                                    groupId = 0,
                                    name = "ИСТб-20-3",
                                    course = 3,
                                    instituteId = null,
                                    institute = null,
                                    isActive = true
                                )
                            ),
                            teachersVerbose = "Куклина М.В.",
                            teachers = listOf(
                                Teacher(
                                    teacherId = 0,
                                    fullName = "Куклина Мария Владимировна",
                                    shortname = "Куклина М.В."
                                )
                            ),
                            classroomId = 0,
                            classroom = Classroom(
                                classroomId = 0,
                                name = "К-305"
                            ),
                            classroomVerbose = "К-305",
                            disciplineId = 0,
                            discipline = null,
                            disciplineVerbose = "Технологическое предпринимательство",
                            otherDisciplineId = 0,
                            otherDiscipline = null,
                            queryId = 0,
                            query = null,
                            lessonId = 3,
                            lessonTime = LessonTime(
                                lessonId = 5,
                                lessonNumber = "5",
                                begTime = "15:30",
                                endTime = "17:00"
                            ),
                            subgroup = 0,
                            lessonType = LessonType.LECTURE,
                            scheduleType = "",
                            date = "2023-11-08"
                        )
                    )
                )
            )
        )
    )
}
