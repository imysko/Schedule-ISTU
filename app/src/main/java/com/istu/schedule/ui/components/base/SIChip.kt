package com.istu.schedule.ui.components.base

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.ui.icons.X
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.BlueContainer
import com.istu.schedule.ui.theme.Shape100

@Composable
fun SIChip(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = modifier
            .clip(Shape100)
            .background(BlueContainer)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 14.dp),
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colorScheme.primary,
            text = text
        )
    }
}

@Composable
fun SIInputChip(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (String) -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(Shape100)
            .background(BlueContainer)
            .padding(vertical = 5.dp, horizontal = 14.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) { onClick(text) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier,
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colorScheme.primary,
            text = text
        )
        Icon(
            modifier = Modifier.padding(start = 3.dp).size(15.dp),
            imageVector = Icons.X,
            contentDescription = "close",
            tint = AppTheme.colorScheme.primary
        )
    }
}

@Composable
fun SITextChip(
    text: String
) {
    Box {
        Text(
            modifier = Modifier.padding(vertical = 5.dp),
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colorScheme.primary,
            text = text
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewSIChip() {
    AppTheme {
        Column {
            SIChip(text = "JavaScript")
            SIInputChip(text = "JavaScript")
            SITextChip(text = "JavaScript")
        }
    }
}
