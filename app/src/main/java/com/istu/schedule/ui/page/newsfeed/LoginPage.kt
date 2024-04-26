package com.istu.schedule.ui.page.newsfeed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.button.FilledButton
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.ShapeTop15

@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(ShapeTop15)
            .background(AppTheme.colorScheme.backgroundSecondary)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.newsfeed_screen_login_hint),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.title.copy(
                color = AppTheme.colorScheme.textPrimary
            )
        )
        FilledButton(
            text = stringResource(R.string.login),
            onClick = { onLoginClick() }
        )
    }
}

@Preview(device = Devices.PIXEL_4, locale = "ru")
@Composable
fun PreviewLoginPage() {
    AppTheme {
        LoginPage()
    }
}
