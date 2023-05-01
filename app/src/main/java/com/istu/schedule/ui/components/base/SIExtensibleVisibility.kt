package com.istu.schedule.ui.components.base

import androidx.compose.animation.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.Composable

@Composable
fun SIExtensibleVisibility(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically(),
        content = content
    )
}
