package com.istu.schedule.ui.components.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.theme.BlueContainer
import com.istu.schedule.ui.theme.ScheduleISTUTheme
import com.istu.schedule.ui.theme.Shape100

@Composable
fun SIChip(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(
        modifier = modifier
            .clip(Shape100)
            .background(BlueContainer),
    ) {
        Text(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 14.dp),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            text = text,
        )
    }
}

@Composable
fun SITextChip(
    text: String,
) {
    Box {
        Text(
            modifier = Modifier.padding(vertical = 5.dp),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            text = text,
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewSIChip() {
    ScheduleISTUTheme {
        SIChip(text = "JavaScript")
    }
}