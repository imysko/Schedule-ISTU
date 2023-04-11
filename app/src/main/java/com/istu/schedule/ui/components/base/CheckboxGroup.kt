package com.istu.schedule.ui.components.base

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CheckboxGroup(
    modifier: Modifier = Modifier,
    items: List<CheckboxItem>,
) {
    Column(
        modifier = modifier,
    ) {
        for (item in items) {
            SICheckbox(
                modifier = Modifier.padding(top = 10.dp),
                text = item.text,
                onCheckedChange = { item.isChecked = !item.isChecked },
            )
        }
    }
}

data class CheckboxItem(
    val id: Int,
    val text: String,
    var isChecked: Boolean = false,
)