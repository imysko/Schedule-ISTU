package com.istu.schedule.domain.model

import java.time.DayOfWeek
import java.time.Month

data class DateOnly(
    val year: Int,
    val month: Month,
    val day: Int,
    val dayOfWeek: DayOfWeek,
    val dayOfYear: Int,
    val dayNumber: Int
)
