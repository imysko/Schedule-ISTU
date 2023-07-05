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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Participation
import com.istu.schedule.domain.model.projfair.SampleParticipationProvider
import com.istu.schedule.ui.icons.Delete
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Red
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.theme.Shape100
import com.istu.schedule.util.toParticipationPriorityText
import com.istu.schedule.util.toParticipationRomanNumerals

@Composable
fun ParticipationItem(
    participation: Participation,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit = {},
    onClick: () -> Unit = {},
    isEditMode: Boolean
) {
    Card(
        shape = Shape10,
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ParticipationPriorityItem(
                    modifier = Modifier.weight(1f),
                    priority = participation.priority
                )
                if (isEditMode) {
                    Spacer(Modifier.width(10.dp))
                    Column(
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { onDeleteClick() }
                            .clip(RoundedCornerShape(5.dp))
                            .border(1.dp, Red, RoundedCornerShape(5.dp))
                            .padding(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Delete,
                            contentDescription = "delete icon",
                            tint = Red
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.padding(top = 18.dp, bottom = 12.dp),
                text = participation.project?.title ?: stringResource(R.string.unknown),
                style = AppTheme.typography.title.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = participation.project?.goal ?: stringResource(R.string.unknown),
                style = AppTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ParticipationPriorityItem(priority: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(Shape100)
            .border(
                1.dp,
                if (priority in 1..3) {
                    AppTheme.colorScheme.primary
                } else {
                    AppTheme.colorScheme.error
                },
                Shape100
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box {}
        Text(
            modifier = Modifier.padding(vertical = 6.dp),
            text = priority.toParticipationPriorityText().uppercase(),
            style = AppTheme.typography.bodySmall,
            color = if (priority in 1..3) {
                AppTheme.colorScheme.primary
            } else {
                AppTheme.colorScheme.error
            }
        )
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(Shape100)
                .background(
                    if (priority in 1..3) {
                        AppTheme.colorScheme.primary
                    } else {
                        AppTheme.colorScheme.error
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = priority.toParticipationRomanNumerals(),
                style = AppTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                color = AppTheme.colorScheme.textSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun PreviewParticipation(
    @PreviewParameter(SampleParticipationProvider::class) participation: Participation
) {
    AppTheme {
        ParticipationItem(participation = participation, isEditMode = false)
    }
}

@Preview
@Composable
fun PreviewParticipationEditMode(
    @PreviewParameter(SampleParticipationProvider::class) participation: Participation
) {
    AppTheme {
        ParticipationItem(participation = participation, isEditMode = true)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewParticipationPriorityItem() {
    AppTheme {
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
