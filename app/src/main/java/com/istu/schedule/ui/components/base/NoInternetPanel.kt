package com.istu.schedule.ui.components.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.ui.icons.NoConnection
import com.istu.schedule.ui.theme.AppTheme

@Composable
fun NoInternetPanel() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppTheme.colorScheme.backgroundSecondary),
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.NoConnection,
            contentDescription = null,
            modifier = Modifier.size(128.dp),
            tint = Color.Unspecified
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.no_internet_connection),
            style = AppTheme.typography.title.copy(
                fontWeight = FontWeight.Bold
            ),
            color = AppTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewNoInternetPanel() {
    AppTheme {
        NoInternetPanel()
    }
}
