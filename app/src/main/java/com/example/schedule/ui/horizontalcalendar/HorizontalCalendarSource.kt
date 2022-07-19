package com.example.schedule.ui.horizontalcalendar

import androidx.paging.PageKeyedDataSource
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class HorizontalCalendarSource(
    private val _now: () -> Instant
) : PageKeyedDataSource<Long, Day>() {

    private val _today: LocalDate
        get() = _now().atZone(ZoneId.systemDefault()).toLocalDate()

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Day>
    ) {
        val day = Day(true, _today)
        callback.onResult(mutableListOf(day), -1, 1)
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Day>) {
        val previousDay = _today.plusDays(params.key)
        val day = Day(false, previousDay)
        callback.onResult(mutableListOf(day), params.key - 1)
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Day>) {
        val nextDay = _today.plusDays(params.key)
        val day = Day(false, nextDay)
        callback.onResult(mutableListOf(day), params.key + 1)
    }
}