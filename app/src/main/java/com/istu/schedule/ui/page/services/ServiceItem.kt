package com.istu.schedule.ui.page.services

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.ui.components.base.SIAnimatedVisibility
import com.istu.schedule.ui.icons.Forward
import com.istu.schedule.ui.theme.AppTheme

@Composable
fun ServiceItem(
    title: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = if (onClick != null) rememberRipple() else null,
                onClick = { onClick?.invoke() }
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(5.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = title,
                    style = AppTheme.typography.subtitle
                )
                SIAnimatedVisibility(visible = description != null) {
                    Text(
                        text = description ?: "",
                        style = AppTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = if (onClick != null) {
                                AppTheme.colorScheme.primary
                            } else {
                                AppTheme.colorScheme.secondary
                            }
                        )
                    )
                }
            }

            Icon(
                modifier = Modifier.size(8.dp),
                imageVector = Icons.Forward,
                tint = AppTheme.colorScheme.secondary,
                contentDescription = stringResource(id = R.string.forward)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTwoRowSettingItem() {
    AppTheme {
        ServiceItem(
            title = "Title",
            description = "Description",
            onClick = {}
        )
    }
}
