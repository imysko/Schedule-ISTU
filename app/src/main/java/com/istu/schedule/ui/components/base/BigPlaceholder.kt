package com.istu.schedule.ui.components.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape10

@Composable
fun BigPlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .placeholder(
                visible = true,
                color = AppTheme.colorScheme.textPrimary.copy(alpha = 0.05f),
                shape = Shape10,
                highlight = PlaceholderHighlight.fade(
                    highlightColor = AppTheme.colorScheme.textPrimary.copy(alpha = 0.08f)
                )
            )
    )
}
