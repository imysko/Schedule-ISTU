package com.example.schedule.ui.horizontalcalendar

import androidx.paging.DataSource
import java.time.Instant

class HorizontalCalendarFactory(
    private val _now: () -> Instant
) : DataSource.Factory<Long, Day>() {

    override fun create(): DataSource<Long, Day> {
        return HorizontalCalendarSource(_now)
    }
}