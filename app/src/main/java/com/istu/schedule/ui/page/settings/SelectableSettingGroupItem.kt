package com.istu.schedule.ui.page.settings

import android.view.SoundEffectConstants
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectableSettingGroupItem(
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    title: String,
    description: String? = null,
    icon: ImageVector? = null,
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(8.dp, 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = title,
                    modifier = Modifier.padding(start = 8.dp, end = 16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    maxLines = if (description == null) 2 else 1,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                description?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        maxLines = 2,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
