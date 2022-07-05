package com.example.timetable.ui.bindings

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.example.timetable.R
import com.example.timetable.entities.Lesson
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*

@BindingAdapter("bind_exams_header")
fun TextView.bindExamHeader(companion: String) {
    this.text = resources.getString(R.string.exams_header, companion)
}

@BindingAdapter("bind_selected_date")
fun TextView.bindSelectedDate(companion: LocalDate) {
    this.text = "${companion.dayOfMonth} ${companion.month} ${companion.year}"
}

@BindingAdapter("bind_item_date_theme")
fun ConstraintLayout.bindItemDateTheme(companion: Boolean) {
    if (companion)
        this.background = ResourcesCompat.getDrawable(resources, R.drawable.item_select_date_border, null)
    else
        this.background = ResourcesCompat.getDrawable(resources, R.drawable.item_deselect_date_border, null)
}


@BindingAdapter("bind_type_week")
fun TextView.bindTypeWeek(companion: LocalDate) {
    val weekNumber = companion.get(WeekFields.of(Locale.UK).weekBasedYear())
    this.text = if (weekNumber % 2 == 0)
        resources.getString(R.string.even_week)
    else
        resources.getString(R.string.odd_week)
}

@BindingAdapter("bind_lesson_time")
fun TextView.bindLessonTime(companion: String) {
    var localTime = LocalTime.parse(companion, DateTimeFormatter.ofPattern("H:mm"))
    localTime = localTime.plusMinutes(90)

    this.text = "$companion - $localTime"
}

@BindingAdapter("bind_lesson_number")
fun TextView.bindLessonNumber(companion: Lesson) {
    val lessonNumber: MutableMap<String, Int> = HashMap()
    lessonNumber["8:15"] = 1
    lessonNumber["10:00"] = 2
    lessonNumber["11:45"] = 3
    lessonNumber["13:45"] = 4
    lessonNumber["15:30"] = 5
    lessonNumber["17:10"] = 6

    this.text = lessonNumber[companion.time].toString()
}

@BindingAdapter("bind_lesson_icon")
fun ImageView.bindLessonIcon(companion: String) {
    when (companion) {
        resources.getString(R.string.lecture) ->
            this.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_lecture, null))
        resources.getString(R.string.practice) ->
            this.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_practice, null))
        resources.getString(R.string.laboratory_work) ->
            this.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_laboratory_work, null))
    }
}

@BindingAdapter("bind_exam_date")
fun TextView.bindExamDate(companion: String) {
    if (companion.isEmpty())
        this.text = resources.getString(R.string.exam_date_empty)
    else
        this.text = companion
}