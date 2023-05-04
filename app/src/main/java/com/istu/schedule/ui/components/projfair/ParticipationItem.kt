package com.istu.schedule.ui.components.projfair

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Participation
import com.istu.schedule.ui.theme.ScheduleISTUTheme
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.theme.Shape100
import com.istu.schedule.util.toParticipationPriorityText
import com.istu.schedule.util.totoParticipationRomanNumerals

@Composable
fun ParticipationItem(
    participation: Participation,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        shape = Shape10,
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(18.dp)) {
            ParticipationPriorityItem(participation.priority)
            Text(
                modifier = Modifier.padding(top = 18.dp, bottom = 12.dp),
                text = participation.project?.title ?: stringResource(R.string.unknown),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = participation.project?.goal ?: stringResource(R.string.unknown),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun ParticipationPriorityItem(priority: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Shape100)
            .border(
                1.dp,
                if (priority in 1..3) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.error
                },
                Shape100
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box {}
        Text(
            modifier = Modifier.padding(vertical = 6.dp),
            text = priority.toParticipationPriorityText().uppercase(),
            style = MaterialTheme.typography.bodySmall,
            color = if (priority in 1..3) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.error
            }
        )
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(Shape100)
                .background(
                    if (priority in 1..3) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = priority.totoParticipationRomanNumerals(),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewParticipationPriorityItem() {
    ScheduleISTUTheme {
        Column(modifier = Modifier.width(300.dp)) {
            ParticipationPriorityItem(1)
            Spacer(modifier = Modifier.height(10.dp))
            ParticipationPriorityItem(2)
            Spacer(modifier = Modifier.height(10.dp))
            ParticipationPriorityItem(3)
            Spacer(modifier = Modifier.height(10.dp))
            ParticipationPriorityItem(4)
        }
    }
}
