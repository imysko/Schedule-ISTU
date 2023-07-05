package com.istu.schedule.ui.components.base

import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties

@Composable
fun SIDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    properties: DialogProperties = DialogProperties(),
    onDismissRequest: () -> Unit = {},
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    backgroundColor: Color,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null
) {
    if (visible) {
        AlertDialog(
            properties = properties,
            modifier = modifier,
            onDismissRequest = onDismissRequest,
            title = title,
            text = text,
            backgroundColor = backgroundColor,
            confirmButton = confirmButton,
            dismissButton = dismissButton
        )
    }
}
