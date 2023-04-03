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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.istu.schedule.data.model.Week
import com.istu.schedule.ui.fonts.montFamily
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
                    modifier = Modifier.clickable { onSelect(date) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = date.dayOfWeek
                            .getDisplayName(TextStyle.SHORT_STANDALONE, Locale("us"))
                            .toString()
                            .replaceFirstChar {
                                if (it.isLowerCase())
                                    it.titlecase(Locale.getDefault())
                                else
                                    it.toString()
                            },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = montFamily,
                        color = MaterialTheme.colorScheme.background,
                    )
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = date.dayOfMonth.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = montFamily,
                        color = MaterialTheme.colorScheme.background,
                    )
                    if (date == currentDate) {
                        Box(
                            modifier = Modifier
                                .padding(top = 2.dp)
                                .size(4.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.background),
                        )
                    }
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
                color = MaterialTheme.colorScheme.background,
            )
            Text(
                text = "чётная",
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