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
import com.istu.schedule.domain.model.DateOnly
import com.istu.schedule.domain.model.schedule.Classroom
import com.istu.schedule.domain.model.schedule.Discipline
import com.istu.schedule.domain.model.schedule.Lesson
import com.istu.schedule.domain.model.schedule.LessonTime
import com.istu.schedule.domain.model.schedule.Schedule
import com.istu.schedule.ui.fonts.montFamily
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.theme.Shape100
import com.istu.schedule.ui.theme.Shape5
import com.istu.schedule.ui.theme.Shape60
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun ScheduleCard(
    currentDateTime: LocalDateTime,
    lesson: Lesson,
    lessonDate: DateOnly
) {
    val currentDate = currentDateTime.toLocalDate()
    val currentTime = currentDateTime.toLocalTime()

    val timeFormatter = DateTimeFormatter.ofPattern("HH.mm")
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d")
    val begTime = LocalTime.parse(lesson.time.begTime)
    val endTime = LocalTime.parse(lesson.time.endTime)
    val date = LocalDate.parse(lessonDate.toString(), dateFormatter)

    val lessonStatus = when {
        currentDate == date && currentTime >= begTime && currentTime <= endTime -> LessonStatus.CURRENT
        currentTime < begTime -> LessonStatus.FUTURE
        currentTime > endTime -> LessonStatus.PAST
        else -> LessonStatus.UNKNOWN
    }

    Card(
        shape = Shape10,
        modifier = Modifier
            .fillMaxWidth(),
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
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = montFamily,
                                color = MaterialTheme.colorScheme.background,
                            )
                        }

                        Text(
                            text = "${begTime.format(timeFormatter)} - ${
                                endTime.format(
                                    timeFormatter
                                )
                            }",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = montFamily,
                        )
                    }
                }

                Text(
                    text = when (lesson.schedules.first().lessonType) {
                        LessonType.LECTURE.ordinal -> stringResource(id = R.string.lecture)
                        LessonType.PRACTICAL.ordinal -> stringResource(id = R.string.practical)
                        LessonType.LABORATORY_WORK.ordinal -> stringResource(id = R.string.laboratory_work)
                        else -> ""
                    },
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = montFamily,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                val schedulesIterator = lesson.schedules.listIterator()

                while (schedulesIterator.hasNext()) {
                    schedulesIterator.next().also {
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(15.dp),
                        ) {
                            Text(
                                text = it.teachersVerbose,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = montFamily,
                                color = MaterialTheme.colorScheme.secondary,
                            )

                            Text(
                                text = it.disciplineVerbose,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = montFamily,
                            )

                            Box(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.primary, Shape5),
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(vertical = 1.dp, horizontal = 7.dp),
                                    text = it.classroomVerbose,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Light,
                                    fontFamily = montFamily,
                                    color = MaterialTheme.colorScheme.background,
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = if (it.subgroup != 0)
                                    Arrangement.SpaceBetween else Arrangement.End,
                            ) {
                                if (it.subgroup != 0) {
                                    Text(
                                        text = "${stringResource(id = R.string.subgroup)} ${it.subgroup}",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal,
                                        fontFamily = montFamily,
                                    )
                                }

                                Text(
                                    text = it.groupsVerbose,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = montFamily,
                                    color = MaterialTheme.colorScheme.secondary,
                                )
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
                            duration
                        ),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = montFamily,
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
        scheduleGroups = emptyList(),
        teachersVerbose = "Харахинов В.А.",
        scheduleTeachers = emptyList(),
        classroomId = 0,
        classroom = Classroom(
            classroomId = 0,
            name = "",
        ),
        classroomVerbose = "Д-105б",
        disciplineId = 0,
        discipline = Discipline(
            disciplineId = 0,
            title = "",
            realTitle = "",
        ),
        disciplineVerbose = "Разработка мобильных приложений",
        lessonId = 0,
        lessonTime = LessonTime(
            lessonId = 0,
            lessonNumber = "4",
            begTime = "13:45",
            endTime = "15:15",
        ),
        subgroup = 1,
        lessonType = 1,
        date = DateOnly(
            year = 2023,
            month = 3,
            day = 31,
            dayOfWeek = DayOfWeek.FRIDAY,
            dayOfYear = 0,
            dayNumber = 0,
        ),
    )

    val lesson = Lesson(
        time = LessonTime(
            lessonId = 0,
            lessonNumber = "4",
            begTime = "13:45",
            endTime = "15:15"
        ),
        schedules = listOf(schedule, schedule)
    )

    val currentDateTime = LocalDateTime.of(2023, 3, 31, 14, 4)
    ScheduleCard(currentDateTime, lesson, lesson.schedules.first().date)
}
