package com.istu.schedule.ui.components.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.ui.icons.Forward
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape10

@Composable
fun TwoRowSettingItem(
    title: String,
    description: String? = null,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Shape10)
            .clickable { onClick?.invoke() }
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = AppTheme.typography.subtitle
                )
                Icon(
                    modifier = Modifier.size(8.dp),
                    imageVector = Icons.Forward,
                    tint = AppTheme.colorScheme.secondary,
                    contentDescription = stringResource(id = R.string.forward)
                )
            }
            if (description != null) {
                Text(
                    text = description,
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
    }
}

@Preview
@Composable
fun PreviewTwoRowSettingItem() {
    AppTheme {
        TwoRowSettingItem(
            title = "Title",
            description = "Description",
            onClick = {}
        )
    }
}
