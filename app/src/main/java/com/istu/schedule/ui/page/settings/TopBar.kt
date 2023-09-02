package com.istu.schedule.ui.page.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.ui.icons.Back
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape5

@Composable
fun TopBar(
    title: String,
    isShowBackButton: Boolean = false,
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(32.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isShowBackButton) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(Shape5)
                        .clickable { onBackClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Back,
                        contentDescription = stringResource(id = R.string.back),
                        tint = AppTheme.colorScheme.textSecondary
                    )
                }
            }

            Text(
                text = title,
                style = AppTheme.typography.pageTitle,
                color = AppTheme.colorScheme.textSecondary
            )
        }
    }
}

@Preview
@Composable
fun PreviewTopBarWithoutBack() {
    AppTheme {
        TopBar(
            title = "Settings"
        )
    }
}

@Preview
@Composable
fun PreviewTopBarWithBack() {
    AppTheme {
        TopBar(
            title = "Settings",
            isShowBackButton = true
        )
    }
}
