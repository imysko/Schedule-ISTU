package com.istu.schedule.domain.entities

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.temporal.WeekFields
import java.util.Locale


data class Week(
    val startDayOfWeek: LocalDate,
) {
    private val _fieldISO = WeekFields.of(Locale.forLanguageTag("ru"))

    val days = mutableListOf<LocalDate>()
    val parity: Parity

    init {
        for (day in 0 until 7) {
            days.add(startDayOfWeek.plusDays(day.toLong()))
        }

        parity = calculateParity(startDayOfWeek)
    }

    private fun calculateParity(startDayOfWeek: LocalDate): Parity {
        val startDateOfStudyYear = getStartDateOfYear(startDayOfWeek)

        return if ((startDayOfWeek.get(_fieldISO.weekOfYear()) - startDateOfStudyYear.get(_fieldISO.weekOfYear())) % 2 != 0) {
            Parity.EVEN
        } else {
            Parity.ODD
        }
    }

    private fun getStartDateOfYear(date: LocalDate): LocalDate {
        val septemberFirst = LocalDate.of(date.year, Month.SEPTEMBER, 1)


        return if (date.month >= Month.SEPTEMBER ||
            septemberFirst.get(_fieldISO.weekOfYear()) == septemberFirst.get(_fieldISO.weekOfYear())) {
                septemberFirst.with(_fieldISO.dayOfWeek(), DayOfWeek.MONDAY.getLong(_fieldISO.dayOfWeek()))
        } else {
            septemberFirst.minusYears(1)
                .with(_fieldISO.dayOfWeek(), DayOfWeek.MONDAY.getLong(_fieldISO.dayOfWeek()))
        }
    }
}
