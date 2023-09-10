package com.istu.schedule.ui.page.ext

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.components.base.SIAnimatedVisibilityFadeOnly
import com.istu.schedule.ui.icons.Logo152
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.ShapeTop15

@Composable
fun LoadingPage(
    title: String,
    isLoading: Boolean
) {
    SIAnimatedVisibilityFadeOnly(isLoading) {
        Scaffold(
            containerColor = AppTheme.colorScheme.backgroundPrimary,
            topBar = { TopBar(title) }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .clip(ShapeTop15)
                    .background(color = AppTheme.colorScheme.backgroundSecondary),
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
}

@Preview
@Composable
fun PreviewLoadingPage() {
    AppTheme {
        LoadingPage(title = "Loading", isLoading = true)
    }
}
