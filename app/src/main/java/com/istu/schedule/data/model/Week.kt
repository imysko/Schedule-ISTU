package com.istu.schedule.data.model

import java.time.LocalDate

data class Week(
    val startDayOfWeek: LocalDate
) {
    val days = mutableListOf<LocalDate>()

    init {
        for (day in 0 until 7) {
            days.add(startDayOfWeek.plusDays(day.toLong()))
        }
    }
}
