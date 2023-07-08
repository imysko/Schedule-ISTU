package com.istu.schedule.ui.components.base

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.icons.Logo152
import com.istu.schedule.ui.theme.AppTheme

@Composable
fun LoadingPanel(
    isLoading: Boolean
) {
    AnimatedVisibility(
        visible = isLoading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppTheme.colorScheme.background.copy(alpha = 0.75f))
                .alpha(0.75f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Logo152,
                contentDescription = null,
                modifier = Modifier.size(90.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@Preview
@Composable
fun PreviewLoadingPanel() {
    AppTheme {
        LoadingPanel(isLoading = true)
    }
}
