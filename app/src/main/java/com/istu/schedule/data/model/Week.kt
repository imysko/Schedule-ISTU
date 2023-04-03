package com.istu.schedule.data.model

import java.time.LocalDate

data class Week(
    val startDayOfWeek: LocalDate,
) {
    val endDayOfWeek: LocalDate = startDayOfWeek.plusDays(6)
    val days = mutableListOf<LocalDate>()
    
    init {
        for (day in 0 until 7) {
            days.add(startDayOfWeek.plusDays(day.toLong()))
        }
    }
}
