package com.istu.schedule.ui.components.base

import android.view.SoundEffectConstants
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.icons.Account
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.HalfGray

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    val view = LocalView.current

    Surface(
        modifier = modifier
            .clickable {
                view.playSoundEffect(SoundEffectConstants.CLICK)
                onClick()
            }
            .alpha(if (enable) 1f else 0.5f),
        color = Color.Unspecified
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(20.dp),
                        tint = AppTheme.colorScheme.textPrimary
                    )
                    Text(
                        text = title,
                        maxLines = 1,
                        style = AppTheme.typography.subtitle,
                        color = AppTheme.colorScheme.textPrimary
                    )
                }
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowRight,
                    contentDescription = title,
                    modifier = Modifier.size(16.dp),
                    tint = AppTheme.colorScheme.textPrimary
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = HalfGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsItem() {
    AppTheme {
        SettingsItem(
            title = "Account",
            icon = Icons.Account
        ) {
        }
    }
}
