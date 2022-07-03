package com.example.timetable.ui.horizontalcalendar

import androidx.paging.PageKeyedDataSource
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class HorizontalCalendarSource(
    private val _now: () -> Instant
) : PageKeyedDataSource<Long, LocalDate>() {

    private val _today: LocalDate
        get() = _now().atZone(ZoneId.systemDefault()).toLocalDate()

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, LocalDate>
    ) {
        callback.onResult(mutableListOf(_today), -1, 1)
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, LocalDate>) {
        val previousDay = _today.plusDays(params.key)
        callback.onResult(mutableListOf(previousDay), params.key - 1)
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, LocalDate>) {
        val nextDay = _today.plusDays(params.key)
        callback.onResult(mutableListOf(nextDay), params.key + 1)
    }
}