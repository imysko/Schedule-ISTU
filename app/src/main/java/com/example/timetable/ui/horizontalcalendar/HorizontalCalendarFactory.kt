package com.example.timetable.ui.horizontalcalendar

import androidx.paging.DataSource
import java.time.Instant
import java.time.LocalDate

class HorizontalCalendarFactory(
    private val _now: () -> Instant
) : DataSource.Factory<Long, Day>() {

    override fun create(): DataSource<Long, Day> {
        return HorizontalCalendarSource(_now)
    }
}