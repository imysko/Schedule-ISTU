package com.istu.schedule.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.offset

fun Modifier.decreasePadding(value: Dp): Modifier {
    return this.layout { measurable, constraints ->
        val placeable =
            measurable.measure(
                constraints.offset(horizontal = value.roundToPx() * 2)
            )
        layout(
            placeable.width - value.roundToPx() * 2,
            placeable.height
        ) {
            placeable.place(-value.roundToPx(), 0)
        }
    }
}
