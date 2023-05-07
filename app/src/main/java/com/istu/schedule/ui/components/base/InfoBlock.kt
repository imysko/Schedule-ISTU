package com.istu.schedule.ui.components.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.istu.schedule.ui.theme.ScheduleISTUTheme

@Composable
fun InfoBlock(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Normal
            )
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                .placeholder(
                    visible = description.isBlank(),
                    color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(4.dp),
                    highlight = PlaceholderHighlight.fade(
                        highlightColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.15f)
                    )
                ),
            text = description,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInfoBlock() {
    ScheduleISTUTheme {
        InfoBlock(
            title = "Учебная группа",
            description = "ИСТб-20-1"
        )
    }
}
