package com.istu.schedule.ui.components.base.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.ui.theme.AppTheme

@Composable
fun TextButton(
    text: String,
    modifier: Modifier = Modifier,
    contentColor: Color = AppTheme.colorScheme.primary,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .height(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text.uppercase(),
            style = AppTheme.typography.button,
            color = contentColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextButtonPreview() {
    AppTheme {
        Column {
            TextButton(
                modifier = Modifier.fillMaxWidth().height(42.dp),
                text = stringResource(R.string.read_more)
            )
        }
    }
}
