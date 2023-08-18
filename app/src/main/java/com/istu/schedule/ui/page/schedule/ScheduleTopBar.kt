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
import com.istu.schedule.data.model.Week
import com.istu.schedule.ui.components.calendar.HorizontalCalendar
import com.istu.schedule.ui.icons.Search
import com.istu.schedule.ui.theme.AppTheme
import java.time.LocalDate

@Composable
fun ScheduleTopBar(
    scheduleUiState: ScheduleUiState,
    weeksList: List<Week>,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    onSearchButtonClick: () -> Unit,
    onDateSelect: (LocalDate) -> Unit,
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
            Text(
                text = stringResource(id = R.string.title_schedule),
                style = AppTheme.typography.pageTitle,
                color = AppTheme.colorScheme.textSecondary
            )

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

        if (scheduleUiState.isShowDescription) {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = scheduleUiState.description ?: "",
                style = AppTheme.typography.title,
                color = AppTheme.colorScheme.textSecondary
            )
        }

        HorizontalCalendar(
            weeksList = weeksList,
            currentDate = currentDate,
            selectedDate = selectedDate,
            calendarState = scheduleUiState.calendarState,
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
                scheduleUiState = ScheduleUiState(),
                weeksList = listOf(
                    Week(LocalDate.of(2023, 6, 12))
                ),
                currentDate = LocalDate.of(2023, 6, 13),
                selectedDate = LocalDate.of(2023, 6, 15),
                onSearchButtonClick = {  },
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
                scheduleUiState = ScheduleUiState(
                    isShowDescription = true,
                    description = "ИСТб-20-3"
                ),
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