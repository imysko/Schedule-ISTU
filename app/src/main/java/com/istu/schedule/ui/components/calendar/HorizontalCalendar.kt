package com.istu.schedule.ui.components.calendar

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.istu.schedule.data.model.Week
import com.istu.schedule.ui.fonts.interFamily
import com.istu.schedule.ui.theme.AppTheme
import java.time.LocalDate
import java.time.format.TextStyle
import kotlinx.coroutines.launch

@Composable
fun HorizontalCalendar(
    modifier: Modifier = Modifier,
    weeksList: List<Week> = emptyList(),
    currentDate: LocalDate = LocalDate.now(),
    selectedDate: LocalDate = LocalDate.now(),
    calendarState: LazyListState,
    onSelect: (selectedDate: LocalDate) -> Unit
) {
    val lazyStackScope = rememberCoroutineScope()

    val scrollSpeedDivisor = 1f
    var sumScrollValue = 0f

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            modifier = modifier
                .padding(top = 15.dp)
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { _, dragAmount ->
                            lazyStackScope.launch {
                                val scrollValue = -dragAmount / scrollSpeedDivisor
                                calendarState.scrollBy(scrollValue)
                                sumScrollValue += scrollValue
                            }
                        },
                        onDragEnd = {
                            lazyStackScope.launch {
                                if (sumScrollValue > 200) {
                                    sumScrollValue = 0f
                                    calendarState.animateScrollToItem(
                                        calendarState.firstVisibleItemIndex + 1
                                    )
                                } else if (sumScrollValue < -200) {
                                    sumScrollValue = 0f
                                    calendarState.animateScrollToItem(
                                        calendarState.firstVisibleItemIndex
                                    )
                                } else if (sumScrollValue < 0) {
                                    sumScrollValue = 0f
                                    calendarState.animateScrollToItem(
                                        calendarState.firstVisibleItemIndex + 1
                                    )
                                } else {
                                    sumScrollValue = 0f
                                    calendarState.animateScrollToItem(
                                        calendarState.firstVisibleItemIndex
                                    )
                                }
                            }
                        }
                    )
                },
            state = calendarState,
            userScrollEnabled = false
        ) {
            items(weeksList) {
                Box(modifier = Modifier.fillParentMaxWidth()) {
                    Week(
                        week = it,
                        currentDate = currentDate,
                        selectedDate = selectedDate,
                        onSelect = onSelect
                    )
                }
            }
        }

        Row(
            modifier = Modifier.padding(top = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = selectedDate.dayOfMonth.toString(),
                fontWeight = FontWeight.Bold,
                fontFamily = interFamily,
                fontSize = 12.sp,
                color = AppTheme.colorScheme.backgroundSecondary
            )
            Text(
                text = selectedDate.month.getDisplayName(
                    TextStyle.FULL,
                    LocalContext.current.resources.configuration.locales.get(0)
                ),
                fontWeight = FontWeight.Bold,
                fontFamily = interFamily,
                fontSize = 12.sp,
                color = AppTheme.colorScheme.backgroundSecondary
            )
        }
    }
}

@Composable
@Preview(showBackground = false)
fun HorizontalCalendarPreview() {
    HorizontalCalendar(calendarState = rememberLazyListState(), onSelect = { })
}
