package com.istu.schedule.domain.model

import java.time.DayOfWeek

data class DateOnly(
    val year: Int,
    val month: Int,
    val day: Int,
    val dayOfWeek: DayOfWeek,
    val dayOfYear: Int,
    val dayNumber: Int,
) {
    override fun toString(): String {
        return "$year-$month-$day"
    }
}
