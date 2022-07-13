package com.example.timetable.ui.bindings

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.example.timetable.R
import com.example.timetable.entities.Lesson
import com.google.android.material.divider.MaterialDivider
import kotlinx.android.synthetic.main.fragment_schedule.view.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*

@BindingAdapter("bind_exams_header")
fun TextView.bindExamHeader(group: String) {
    this.text = resources.getString(R.string.exams_header, group)
}

@BindingAdapter("bind_selected_date")
fun TextView.bindSelectedDate(date: LocalDate) {
    this.text = "${date.dayOfMonth} ${date.month} ${date.year}"
}

@BindingAdapter("bind_item_date_theme")
fun ConstraintLayout.bindItemDateTheme(isSelected: Boolean) {
    if (isSelected)
        this.background = ResourcesCompat.getDrawable(resources, R.drawable.item_select_date_border, null)
    else
        this.background = ResourcesCompat.getDrawable(resources, R.drawable.item_deselect_date_border, null)
}


@BindingAdapter("bind_type_week")
fun TextView.bindTypeWeek(date: LocalDate) {
    val weekNumber = date.get(WeekFields.of(Locale.UK).weekBasedYear())
    this.text = if (weekNumber % 2 == 0)
        resources.getString(R.string.even_week)
    else
        resources.getString(R.string.odd_week)
}

@BindingAdapter("bind_lesson_time")
fun TextView.bindLessonTime(dateTime: LocalDateTime) {
    val endTime = dateTime.toLocalTime().plusMinutes(90)

    this.text = "${dateTime.toLocalTime()} - $endTime"
}

@BindingAdapter("bind_lesson_number")
fun TextView.bindLessonNumber(lesson: Lesson) {
    val lessonNumber: MutableMap<String, Int> = HashMap()
    lessonNumber["8:15"] = 1
    lessonNumber["10:00"] = 2
    lessonNumber["11:45"] = 3
    lessonNumber["13:45"] = 4
    lessonNumber["15:30"] = 5
    lessonNumber["17:10"] = 6

    this.text = lessonNumber[lesson.time].toString()
}

@BindingAdapter("bind_lesson_icon")
fun ImageView.bindLessonIcon(lessonType: String) {
    when (lessonType) {
        resources.getString(R.string.lecture) ->
            this.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_lecture, null))
        resources.getString(R.string.practice) ->
            this.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_practice, null))
        resources.getString(R.string.laboratory_work) ->
            this.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_laboratory_work, null))
    }
}

enum class STATUS {
    PAST,
    NEXT,
    CURRENT,
    DEFAULT
}

fun definitionStatus(dateTime: LocalDateTime) : STATUS {
    val startTime = dateTime.toLocalTime()
    val endTime = startTime.plusMinutes(90)

    return when {
        dateTime.toLocalDate() != LocalDate.now() -> STATUS.DEFAULT
        else -> {
            when {
                LocalTime.now() in startTime..endTime -> STATUS.CURRENT
                startTime > LocalTime.now() -> STATUS.NEXT
                endTime < LocalTime.now() -> STATUS.PAST
                else -> STATUS.DEFAULT
            }
        }
    }
}

@BindingAdapter("bind_lesson_status")
fun LinearLayout.bindLessonStatus(dateTime: LocalDateTime) {
    when (definitionStatus(dateTime)) {
        STATUS.NEXT ->
            this.background = ResourcesCompat.getDrawable(resources, R.drawable.lesson_status_next, null)
        STATUS.CURRENT ->
            this.background = ResourcesCompat.getDrawable(resources, R.drawable.lesson_status_current, null)
        STATUS.PAST ->
            this.background = ResourcesCompat.getDrawable(resources, R.drawable.lesson_status_past, null)
        STATUS.DEFAULT ->
            this.background = ResourcesCompat.getDrawable(resources, R.drawable.lesson_status_default, null)
    }
}

@BindingAdapter("bind_lesson_time_status")
fun LinearLayout.bindLessonTimeStatus(dateTime: LocalDateTime) {
    when (definitionStatus(dateTime)) {
        STATUS.NEXT ->
            this.background = ResourcesCompat.getDrawable(resources, R.drawable.lesson_time_status_next, null)
        STATUS.CURRENT ->
            this.background = ResourcesCompat.getDrawable(resources, R.drawable.lesson_time_status_current, null)
        STATUS.PAST ->
            this.background = ResourcesCompat.getDrawable(resources, R.drawable.lesson_time_status_past, null)
        STATUS.DEFAULT ->
            this.background = ResourcesCompat.getDrawable(resources, R.drawable.lesson_time_status_default, null)
    }
}

@BindingAdapter("bind_color_divider")
fun MaterialDivider.bindColorDivider(dateTime: LocalDateTime) {
    when (definitionStatus(dateTime)) {
        STATUS.NEXT ->
            this.dividerColor = resources.getColor(R.color.malachite)
        STATUS.CURRENT ->
            this.dividerColor = resources.getColor(R.color.orange)
        STATUS.PAST ->
            this.dividerColor = resources.getColor(R.color.middle_gray)
        STATUS.DEFAULT ->
            this.dividerColor = resources.getColor(R.color.middle_blue)
    }
}

@BindingAdapter("bind_exam_date")
fun TextView.bindExamDate(date: String) {
    if (date.isEmpty())
        this.text = resources.getString(R.string.exam_date_empty)
    else
        this.text = date
}