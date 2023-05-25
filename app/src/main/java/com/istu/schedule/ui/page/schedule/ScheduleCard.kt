package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.istu.schedule.R
import com.istu.schedule.data.enums.LessonStatus
import com.istu.schedule.data.enums.LessonType
import com.istu.schedule.domain.model.schedule.Classroom
import com.istu.schedule.domain.model.schedule.Lesson
import com.istu.schedule.domain.model.schedule.LessonTime
import com.istu.schedule.domain.model.schedule.Schedule
import com.istu.schedule.ui.fonts.interFamily
import com.istu.schedule.ui.theme.ScheduleISTUTheme
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.theme.Shape100
import com.istu.schedule.ui.theme.Shape5
import com.istu.schedule.ui.theme.Shape60
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun ScheduleCard(
    currentDateTime: LocalDateTime,
    lesson: Lesson,
    lessonDate: String,
) {
    val currentDate = currentDateTime.toLocalDate()
    val currentTime = currentDateTime.toLocalTime()

    val timeFormatter = DateTimeFormatter.ofPattern("H.mm")
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d")
    val begTime = LocalTime.parse(lesson.time.begTime, DateTimeFormatter.ofPattern("H:mm"))
    val endTime = LocalTime.parse(lesson.time.endTime, DateTimeFormatter.ofPattern("H:mm"))
    val date = LocalDate.parse(lessonDate, dateFormatter)

    val lessonStatus = when {
        currentDate == date && currentTime >= begTime && currentTime <= endTime -> LessonStatus.CURRENT
        currentTime < begTime -> LessonStatus.FUTURE
        currentTime > endTime -> LessonStatus.PAST
        else -> LessonStatus.UNKNOWN
    }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = Shape10,
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 15.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            color = if (lessonStatus == LessonStatus.CURRENT) {
                                MaterialTheme.colorScheme.errorContainer
                            } else {
                                MaterialTheme.colorScheme.primaryContainer
                            },
                            shape = Shape100,
                        ),
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 5.dp, end = 12.dp, top = 4.dp, bottom = 4.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .background(
                                    color = if (lessonStatus == LessonStatus.CURRENT) {
                                        MaterialTheme.colorScheme.error
                                    } else {
                                        MaterialTheme.colorScheme.primary
                                    },
                                    shape = Shape60,
                                ),
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 7.dp),
                                text = lesson.time.lessonNumber,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.background,
                                ),
                            )
                        }

                        Text(
                            text = "${begTime.format(timeFormatter)} - ${
                                endTime.format(
                                    timeFormatter,
                                )
                            }",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }

                Text(
                    text = when (lesson.schedules.first().lessonType) {
                        LessonType.LECTURE -> stringResource(id = R.string.lecture)
                        LessonType.PRACTICAL -> stringResource(id = R.string.practical)
                        LessonType.LABORATORY_WORK -> stringResource(id = R.string.laboratory_work)
                        LessonType.PROJECT -> stringResource(id = R.string.project)
                        else -> ""
                    },
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 13.sp,
                    ),
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                val schedulesIterator = lesson.schedules.listIterator()

                while (schedulesIterator.hasNext()) {
                    schedulesIterator.next().also { schedule ->
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(5.dp),
                            ) {

                                schedule.teachers?.let {
                                    Text(
                                        text = schedule.teachers.joinToString(
                                            separator = "\n",
                                            transform = { it.fullName }
                                        ),
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = MaterialTheme.colorScheme.secondary,
                                        ),
                                    )
                                }

                                var disciplineTitle = schedule.disciplineVerbose
                                schedule.discipline?.let {
                                    disciplineTitle = it.title
                                }
                                schedule.otherDiscipline?.let {
                                    disciplineTitle = it.disciplineTitle
                                }
                                schedule.query?.let {
                                    disciplineTitle = it.description
                                }

                                Text(
                                    text = disciplineTitle,
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = FontWeight.SemiBold,
                                    ),
                                )

                                schedule.classroom?.let {
                                    Box(
                                        modifier = Modifier
                                            .background(MaterialTheme.colorScheme.primary, Shape5),
                                    ) {
                                        Text(
                                            modifier = Modifier
                                                .padding(vertical = 1.dp, horizontal = 7.dp),
                                            text = schedule.classroomVerbose,
                                            style = MaterialTheme.typography.titleLarge.copy(
                                                fontWeight = FontWeight.Light,
                                                color = MaterialTheme.colorScheme.background,
                                            ),
                                        )
                                    }
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = if (schedule.subgroup != 0) {
                                    Arrangement.SpaceBetween
                                } else {
                                    Arrangement.End
                                },
                            ) {
                                if (schedule.subgroup != 0) {
                                    Text(
                                        text = "${stringResource(id = R.string.subgroup)} ${schedule.subgroup}",
                                        style = MaterialTheme.typography.bodySmall,
                                    )
                                }

                                schedule.groups?.let {
                                    Text(
                                        text = schedule.groups.joinToString(
                                            separator = ", ",
                                            limit = 3,
                                            transform = { it.name!! },
                                        ),
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = MaterialTheme.colorScheme.secondary,
                                        ),
                                    )
                                }
                            }
                        }
                    }

                    if (schedulesIterator.hasNext()) {
                        Divider(modifier = Modifier.padding(horizontal = 1.dp))
                    }
                }
            }

            if (lessonStatus == LessonStatus.CURRENT) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Divider(modifier = Modifier.padding(horizontal = 1.dp))

                    val duration = Duration.between(currentTime, endTime).toMinutes().toInt()
                    Text(
                        text = pluralStringResource(
                            id = R.plurals.remained_time,
                            count = duration,
                            duration,
                        ),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = interFamily,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = false, locale = "ru")
fun ScheduleCardPreview() {
    val schedule = Schedule(
        scheduleId = 0,
        groupsVerbose = "ИСТб-20-3",
        groups = emptyList(),
        teachersVerbose = "Харахинов В.А.",
        teachers = emptyList(),
        classroomId = 0,
        classroom = Classroom(
            classroomId = 0,
            name = "",
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
            endTime = "15:15",
        ),
        subgroup = 1,
        lessonType = LessonType.LECTURE,
        scheduleType = "",
        date = "2023-03-31",
    )

    val lesson = Lesson(
        time = LessonTime(
            lessonId = 0,
            lessonNumber = "4",
            begTime = "13:45",
            endTime = "15:15",
        ),
        schedules = listOf(schedule, schedule),
    )

    val currentDateTime = LocalDateTime.of(2023, 3, 31, 14, 4)
    ScheduleISTUTheme {
        ScheduleCard(currentDateTime, lesson, lesson.schedules.first().date)
    }
}
