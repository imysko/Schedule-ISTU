package com.istu.schedule.ui.components.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.theme.ScheduleISTUTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SICheckbox(
    modifier: Modifier = Modifier,
    checkedState: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    text: String = "",
) {
    Row(
        modifier = modifier.padding(horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            Checkbox(
                checked = checkedState,
                onCheckedChange = { onCheckedChange(it) },
                modifier = Modifier.padding(end = 15.dp),
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    checkmarkColor = MaterialTheme.colorScheme.onPrimary,
                ),
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSICheckbox() {
    ScheduleISTUTheme {
        Column {
            SICheckbox(checkedState = false, text = "Unchecked")
            SICheckbox(checkedState = true, text = "Checked")
        }
    }
}
