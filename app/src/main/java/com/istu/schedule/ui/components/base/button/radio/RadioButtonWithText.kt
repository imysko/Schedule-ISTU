package com.istu.schedule.ui.components.base.button.radio

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.theme.ScheduleISTUTheme

@Composable
fun RadioButtonWithText(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (enabled) {
                    onSelect()
                }
            }
    ) {
        val style =
            if (enabled) {
                MaterialTheme.typography.bodyMedium
            } else {
                MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        RadioButton(
            selected = selected,
            onClick = { onSelect() }
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            style = style
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewRadioButtonWithText() {
    ScheduleISTUTheme {
        RadioButtonWithText(
            modifier = Modifier.padding(10.dp),
            text = "Text",
            selected = true,
            onSelect = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewRadioButtonWithTextDisabled() {
    ScheduleISTUTheme {
        RadioButtonWithText(
            modifier = Modifier.padding(10.dp),
            text = "Text",
            selected = false,
            onSelect = {},
            enabled = false
        )
    }
}
