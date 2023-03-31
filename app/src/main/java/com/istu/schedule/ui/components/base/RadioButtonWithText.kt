package com.istu.schedule.ui.components.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonWithText(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() },
    ) {
        RadioButton(
            selected = selected,
            onClick = { onSelect() },
            modifier = Modifier
                .padding(start = 8.dp),
        )
        Text(
            text = text,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth(),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun RadioButtonWithTextPreview() {
    RadioButtonWithText(
        text = "Text",
        selected = true,
    ) {
    }
}
