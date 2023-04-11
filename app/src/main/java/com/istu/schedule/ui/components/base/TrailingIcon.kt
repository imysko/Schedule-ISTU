package com.istu.schedule.ui.components.base

import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.istu.schedule.ui.icons.ChevronDown

@Composable
fun TrailingIcon(expanded: Boolean) {
    Icon(
        Icons.ChevronDown,
        null,
        Modifier.rotate(if (expanded) 180f else 0f),
        MaterialTheme.colorScheme.secondary,
    )
}
