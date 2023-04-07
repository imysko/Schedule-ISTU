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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.istu.schedule.data.model.Week
import com.istu.schedule.ui.fonts.interFamily
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
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 2.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            for (date in week.days) {
                Column(
                    modifier = Modifier
                        .size(width = 43.dp, height = 63.dp)
                        .clip(Shape21_5)
                        .background(if (date == selectedDate) Color(0xFF325AD6) else Color.Transparent)
                        .clickable { onSelect(date) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(top = 7.dp),
                        text = date.dayOfWeek
                            .getDisplayName(TextStyle.SHORT_STANDALONE, Locale("ru"))
                            .toString()
                            .replaceFirstChar {
                                if (it.isLowerCase())
                                    it.titlecase(Locale.getDefault())
                                else
                                    it.toString()
                            },
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight(350),
                            color = MaterialTheme.colorScheme.background,
                        ),
                    )
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(if (date == selectedDate) MaterialTheme.colorScheme.background else Color.Transparent),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = if (date == selectedDate) FontWeight.Normal else FontWeight.SemiBold,
                                color = if (date == selectedDate) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
                            ),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(if (date == currentDate) MaterialTheme.colorScheme.background else Color.Transparent),
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 7.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
        ) {
            Text(
                text = selectedDate.toString(),
                fontFamily = interFamily,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.background,
            )
            Text(
                text = "чётная",
                fontFamily = interFamily,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.background,
            )
        }
    }
}

@Composable
@Preview(showBackground = false)
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