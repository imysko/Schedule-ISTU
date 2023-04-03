package com.istu.schedule.ui.components.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalCalendar() {
    val calendarState = rememberLazyListState()
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState = calendarState)

    LazyRow(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        state = calendarState,
        flingBehavior = snapFlingBehavior,
    ) {
        items(5) {
            Box(modifier = Modifier.fillParentMaxWidth()) {
                Week(currentDate = LocalDate.now())
            }
        }
    }
}

@Composable
@Preview(showBackground = false)
fun HorizontalCalendarPreview() {
    HorizontalCalendar()
}