package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.domain.entities.Week
import com.istu.schedule.ui.components.calendar.HorizontalCalendar
import com.istu.schedule.ui.icons.Search
import com.istu.schedule.ui.theme.AppTheme
import java.time.LocalDate

@Composable
fun ScheduleTopBar(
    subtitle: String?,
    calendarState: LazyListState,
    weeksList: List<Week>,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    onSearchButtonClick: () -> Unit,
    onDateSelect: (LocalDate) -> Unit
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(vertical = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(0.7f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.title_schedule),
                    style = AppTheme.typography.pageTitle,
                    color = AppTheme.colorScheme.textSecondary
                )

                subtitle?.let {
                    Text(
                        text = it,
                        style = AppTheme.typography.title,
                        color = AppTheme.colorScheme.textSecondary
                    )
                }
            }

            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = { onSearchButtonClick() },
                content = {
                    Icon(
                        imageVector = Icons.Search,
                        contentDescription = stringResource(id = R.string.search),
                        tint = AppTheme.colorScheme.textSecondary
                    )
                }
            )
        }

        HorizontalCalendar(
            weeksList = weeksList,
            currentDate = currentDate,
            selectedDate = selectedDate,
            calendarState = calendarState,
            onSelect = { onDateSelect(it) }
        )
    }
}

@Composable
@Preview
fun ScheduleTopBarPreview() {
    AppTheme {
        Box(modifier = Modifier.background(AppTheme.colorScheme.primary)) {
            ScheduleTopBar(
                subtitle = null,
                calendarState = LazyListState(),
                weeksList = listOf(
                    Week(LocalDate.of(2023, 6, 12))
                ),
                currentDate = LocalDate.of(2023, 6, 13),
                selectedDate = LocalDate.of(2023, 6, 15),
                onSearchButtonClick = { },
                onDateSelect = { }
            )
        }
    }
}

@Composable
@Preview(name = "With user description")
fun ScheduleTopBarWithUserDescriptionPreview() {
    AppTheme {
        Box(modifier = Modifier.background(AppTheme.colorScheme.primary)) {
            ScheduleTopBar(
                subtitle = "ИСТб-20-3",
                calendarState = LazyListState(),
                weeksList = listOf(
                    Week(LocalDate.of(2023, 6, 12))
                ),
                currentDate = LocalDate.of(2023, 6, 13),
                selectedDate = LocalDate.of(2023, 6, 15),
                onSearchButtonClick = { },
                onDateSelect = { }
            )
        }
    }
}
