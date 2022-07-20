package com.example.schedule.ui.bindings

import android.util.TypedValue
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.example.schedule.R
import com.example.schedule.entities.Lesson
import com.google.android.material.divider.MaterialDivider
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
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
    this.background = if (isSelected)
        ResourcesCompat.getDrawable(resources, R.drawable.item_select_date_border, context.theme)
    else
        ResourcesCompat.getDrawable(resources, R.drawable.item_deselect_date_border, context.theme)
}

@BindingAdapter("bind_text_date_theme")
fun TextView.bindTextDateTheme(isSelected: Boolean) {
    val color = TypedValue()

    this.setTextColor(
        if (isSelected) {
            context.theme.resolveAttribute(com.google.android.material.R.attr.colorBackgroundFloating,
                color,
                true)
            color.data
        }
        else {
            context.theme.resolveAttribute(com.google.android.material.R.attr.colorOnBackground,
                color,
                true)
            color.data
        }
    )
}


@BindingAdapter("bind_type_week")
fun TextView.bindTypeWeek(date: LocalDate) {
    val weekNumber = date.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())
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
    this.setImageDrawable(
        when (lessonType) {
            resources.getString(R.string.lecture) ->
                ResourcesCompat.getDrawable(resources, R.drawable.ic_history_edu, null)
            resources.getString(R.string.practice) ->
                ResourcesCompat.getDrawable(resources, R.drawable.ic_precision_manufacturing, null)
            resources.getString(R.string.laboratory_work) ->
                ResourcesCompat.getDrawable(resources, R.drawable.ic_biotech, null)
            else -> null
        }
    )
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
    this.background = when (definitionStatus(dateTime)) {
        STATUS.NEXT ->
            ResourcesCompat.getDrawable(resources, R.drawable.lesson_status_next, null)
        STATUS.CURRENT ->
            ResourcesCompat.getDrawable(resources, R.drawable.lesson_status_current, null)
        STATUS.PAST ->
            ResourcesCompat.getDrawable(resources, R.drawable.lesson_status_past, context.theme)
        STATUS.DEFAULT ->
            ResourcesCompat.getDrawable(resources, R.drawable.lesson_status_default, context.theme)
    }
}

@BindingAdapter("bind_color_divider")
fun MaterialDivider.bindColorDivider(dateTime: LocalDateTime) {
    val color = TypedValue()

    this.dividerColor = when (definitionStatus(dateTime)) {
        STATUS.NEXT ->
            ResourcesCompat.getColor(resources, R.color.malachite, null)
        STATUS.CURRENT ->
            ResourcesCompat.getColor(resources, R.color.orange, null)
        STATUS.PAST -> {
            context.theme.resolveAttribute(com.google.android.material.R.attr.colorOutline,
                color,
                true)
            color.data
        }
        STATUS.DEFAULT -> {
            context.theme.resolveAttribute(com.google.android.material.R.attr.colorPrimary,
                color,
                true)
            color.data
        }
    }
}

@BindingAdapter("bind_exam_date")
fun TextView.bindExamDate(date: String) {
    this.text = date.ifEmpty { resources.getString(R.string.exam_date_empty) }
}