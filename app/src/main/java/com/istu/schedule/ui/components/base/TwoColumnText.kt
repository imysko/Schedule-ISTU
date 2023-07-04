package com.istu.schedule.ui.components.base

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.istu.schedule.ui.theme.AppTheme

@Composable
fun TwoColumnText(
    modifier: Modifier = Modifier,
    key: String,
    value: String?
) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.weight(0.3f),
            text = key,
            style = AppTheme.typography.bodySmall
        )
        Spacer(Modifier.weight(0.05f))
        Text(
            modifier = Modifier.weight(0.65f),
            text = value?.ifBlank { "-" } ?: "-",
            style = AppTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTwoColumnText() {
    AppTheme {
        TwoColumnText(
            modifier = Modifier.fillMaxWidth(),
            key = "Ожидаемый результат",
            value = "Платформа пригодная для размещения олимпиад, " +
                "анонса других мероприятий интегрированная с moodle"
        )
    }
}
