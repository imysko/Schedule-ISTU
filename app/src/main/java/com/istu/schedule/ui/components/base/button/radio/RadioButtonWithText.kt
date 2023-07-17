package com.istu.schedule.ui.components.base.button.radio

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape10

@Composable
fun RadioButtonWithText(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    style: TextStyle = AppTheme.typography.bodyMedium,
    enabled: Boolean = true
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Shape10)
            .clickable {
                if (enabled) {
                    onSelect()
                }
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            RadioButton(
                selected = selected,
                onClick = { onSelect() }
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                style = if (enabled) style else style.copy(color = AppTheme.colorScheme.secondary)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewRadioButtonWithText() {
    AppTheme {
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
    AppTheme {
        RadioButtonWithText(
            modifier = Modifier.padding(10.dp),
            text = "Text",
            selected = false,
            onSelect = {},
            enabled = false
        )
    }
}
