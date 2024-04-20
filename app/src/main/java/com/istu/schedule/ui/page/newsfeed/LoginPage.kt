package com.istu.schedule.ui.page.newsfeed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
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
            .background(AppTheme.colorScheme.backgroundSecondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.login_to_account),
            style = AppTheme.typography.title.copy(
                color = AppTheme.colorScheme.textPrimary
            )
        )
        FilledButton(
            modifier = Modifier.padding(top = 25.dp).width(250.dp),
            text = stringResource(R.string.authorize_via_campus),
            onClick = { onLoginClick() }
        )
    }
}

@Preview
@Composable
fun PreviewLoginPage() {
    AppTheme {
        Box(Modifier.size(500.dp)) {
            LoginPage()
        }
    }
}
