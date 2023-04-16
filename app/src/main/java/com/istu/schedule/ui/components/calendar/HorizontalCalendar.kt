package com.istu.schedule.ui.components.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.data.model.Week
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalCalendar(
    weeksList: List<Week> = emptyList(),
    currentDate: LocalDate = LocalDate.now(),
    selectedDate: LocalDate = LocalDate.now(),
    calendarState: LazyListState,
    onSelect: (selectedDate: LocalDate) -> Unit,
) {
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState = calendarState)

    LazyRow(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        state = calendarState,
        flingBehavior = snapFlingBehavior,
    ) {
        items(weeksList) {
            Box(modifier = Modifier.fillParentMaxWidth()) {
                Week(
                    week = it,
                    currentDate = currentDate,
                    selectedDate = selectedDate,
                    onSelect = onSelect,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = false)
fun HorizontalCalendarPreview() {
    HorizontalCalendar(calendarState = rememberLazyListState(), onSelect = { })
}
