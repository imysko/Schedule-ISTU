package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.istu.schedule.data.enums.LessonType
import com.istu.schedule.domain.model.DateOnly
import com.istu.schedule.domain.model.schedule.Classroom
import com.istu.schedule.domain.model.schedule.Discipline
import com.istu.schedule.domain.model.schedule.LessonTime
import com.istu.schedule.domain.model.schedule.Schedule
import com.istu.schedule.ui.theme.Shapes
import java.time.DayOfWeek
import java.time.Month

@Composable
fun ScheduleCard(
    schedule: Schedule
) {
    Card(
        shape = Shapes.medium,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${schedule.lessonTime.begtime} - ${schedule.lessonTime.endtime}",
                    fontSize = 16.sp,
                )

                Text(
                    text = when (schedule.lessonType) {
                        LessonType.LECTURE.ordinal -> "лекция"
                        LessonType.PRACTICAL.ordinal -> "практика"
                        LessonType.LABORATORY_WORK.ordinal -> "лабораторная работа"
                        else -> ""
                    },
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                )
            }

            Divider()

            Text(
                text = schedule.disciplineVerbose,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 3
            )

            Text(
                text = schedule.groupsVerbose,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
            )

            Text(
                text = schedule.teachersVerbose,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "subgroup ${schedule.subgroup}",
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                )

                Text(
                    text = schedule.classroomVerbose,
                    fontSize = 14.sp,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
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
            name = ""
        ),
        classroomVerbose = "Д-105б",
        disciplineId = 0,
        discipline = Discipline(
            disciplineId = 0,
            title = "",
            realTitle = ""
        ),
        disciplineVerbose = "Разработка мобильных приложений",
        lessonId = 0,
        lessonTime = LessonTime(
            lessonId = 0,
            lessonNumber = "4",
            begtime = "13:45",
            endtime = "15:15"
        ),
        subgroup = 1,
        lessonType = 0,
        date = DateOnly(
            year = 2023,
            month = Month.MARCH,
            day = 31,
            dayOfWeek = DayOfWeek.FRIDAY,
            dayOfYear = 0,
            dayNumber = 0
        ),
    )

    ScheduleCard(schedule)
}