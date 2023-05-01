package com.istu.schedule.ui.page.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.ui.theme.ScheduleISTUTheme

@Composable
fun OnBoardingContent(
    modifier: Modifier = Modifier,
    painterResource: Painter,
    title: String = "",
    description: String = ""
) {
    Column(
        modifier = modifier.fillMaxHeight(0.75F),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource,
                contentDescription = null,
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun OnBoardingContentPreview() {
    ScheduleISTUTheme {
        OnBoardingContent(
            painterResource = painterResource(id = R.drawable.look_schedule),
            title = stringResource(id = R.string.look_schedule),
            description = stringResource(id = R.string.look_schedule_description),
        )
    }
}