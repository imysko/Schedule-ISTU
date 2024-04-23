package com.istu.schedule.ui.page.services

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.icons.Book
import com.istu.schedule.ui.theme.AppTheme

@Composable
fun NotificationItem(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            ),
        colors = CardColors(
            contentColor = AppTheme.colorScheme.textPrimary,
            containerColor = AppTheme.colorScheme.surface,
            disabledContentColor = AppTheme.colorScheme.textSecondary,
            disabledContainerColor = AppTheme.colorScheme.backgroundSecondary
        )
    ) {
        Row(
            modifier = Modifier
                .heightIn(min = 96.dp, max = 192.dp)
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(36.dp),
                tint = AppTheme.colorScheme.primary
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = title,
                    style = AppTheme.typography.title,
                    color = AppTheme.colorScheme.textPrimary
                )
                Text(
                    text = description,
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewNotificationItem() {
    AppTheme {
        NotificationItem(
            title = "Title",
            description = "Description",
            icon = Icons.Book,
            onClick = {}
        )
    }
}
