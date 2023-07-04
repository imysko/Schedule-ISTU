package com.istu.schedule.ui.components.base.button.radio

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape100

@Composable
fun RadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color =
        if (selected) AppTheme.colorScheme.primary else AppTheme.colorScheme.secondary
    Box(
        modifier = modifier
            .size(20.dp)
            .clip(Shape100)
            .border(1.dp, color, Shape100)
            .clickable(onClick = onClick)
            .padding(3.5.dp)
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .clip(Shape100)
                    .fillMaxSize()
                    .background(color)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewRadioButtonSelected() {
    AppTheme {
        RadioButton(selected = true, onClick = {})
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewRadioButtonDeselected() {
    AppTheme {
        RadioButton(selected = false, onClick = {})
    }
}
