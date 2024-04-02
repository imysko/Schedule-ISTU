package com.istu.schedule.ui.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.istu.schedule.R
import com.istu.schedule.domain.entities.Parity
import com.istu.schedule.domain.entities.Week
import com.istu.schedule.ui.fonts.interFamily
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape21_5
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun Week(
    week: Week,
    currentDate: LocalDate = LocalDate.now(),
    selectedDate: LocalDate = LocalDate.now(),
    onSelect: (selectedDate: LocalDate) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = week.days.first().month
                    .getDisplayName(
                        TextStyle.FULL_STANDALONE,
                        LocalContext.current.resources.configuration.locales.get(0)
                    )
                    .replaceFirstChar {
                        if (it.isLowerCase()) {
                            it.titlecase(Locale.getDefault())
                        } else {
                            it.toString()
                        }
                    },
                fontFamily = interFamily,
                fontSize = 12.sp,
                color = AppTheme.colorScheme.backgroundSecondary
            )

            Text(
                text = when (week.parity) {
                    Parity.EVEN -> stringResource(id = R.string.even)
                    Parity.ODD -> stringResource(id = R.string.odd)
                },
                fontFamily = interFamily,
                fontSize = 12.sp,
                color = AppTheme.colorScheme.backgroundSecondary
            )
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 2.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (date in week.days) {
                Column(
                    modifier = Modifier
                        .size(width = 43.dp, height = 65.dp)
                        .clip(Shape21_5)
                        .background(
                            if (date == selectedDate) Color(0xFF325AD6) else Color.Transparent
                        )
                        .clickable { onSelect(date) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        space = 4.dp,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    Text(
                        text = date.dayOfWeek
                            .getDisplayName(
                                TextStyle.SHORT,
                                LocalContext.current.resources.configuration.locales.get(0)
                            )
                            .replaceFirstChar {
                                if (it.isLowerCase()) {
                                    it.titlecase(Locale.getDefault())
                                } else {
                                    it.toString()
                                }
                            },
                        style = AppTheme.typography.subtitle.copy(
                            fontWeight = FontWeight(350),
                            color = AppTheme.colorScheme.backgroundSecondary
                        )
                    )
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(
                                if (date == selectedDate) {
                                    AppTheme.colorScheme.backgroundSecondary
                                } else {
                                    Color.Transparent
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            style = AppTheme.typography.subtitle.copy(
                                fontWeight = if (date == selectedDate) {
                                    FontWeight.Normal
                                } else {
                                    FontWeight.SemiBold
                                },
                                color = if (date == selectedDate) {
                                    AppTheme.colorScheme.primary
                                } else {
                                    AppTheme.colorScheme.backgroundSecondary
                                }
                            )
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(
                                if (date == currentDate) {
                                    AppTheme.colorScheme.backgroundSecondary
                                } else {
                                    Color.Transparent
                                }
                            )
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = false, locale = "ru")
fun WeekPreview() {
    Week(
        week = Week(
            startDayOfWeek = LocalDate.of(2023, 4, 3)
        ),
        currentDate = LocalDate.of(2023, 4, 4),
        selectedDate = LocalDate.of(2023, 4, 5),
        onSelect = { }
    )
}

@Composable
@Preview(showBackground = false)
fun WeekTodayPreview() {
    Week(
        week = Week(
            startDayOfWeek = LocalDate.of(2023, 4, 3)
        ),
        currentDate = LocalDate.of(2023, 4, 4),
        selectedDate = LocalDate.of(2023, 4, 4),
        onSelect = { }
    )
}
