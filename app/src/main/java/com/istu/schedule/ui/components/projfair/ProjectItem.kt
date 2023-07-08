package com.istu.schedule.ui.components.projfair

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.domain.model.projfair.SampleProjectProvider
import com.istu.schedule.ui.components.base.ChipVerticalGrid
import com.istu.schedule.ui.components.base.SIChip
import com.istu.schedule.ui.components.base.SITextChip
import com.istu.schedule.ui.components.base.button.FilledButton
import com.istu.schedule.ui.components.base.button.OutlineButton
import com.istu.schedule.ui.icons.People
import com.istu.schedule.ui.icons.Star
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.HalfGray
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.util.toProjectDifficulty
import java.text.DateFormat

@Composable
fun ProjectItem(
    modifier: Modifier = Modifier,
    project: Project,
    canCreateParticipation: Boolean = false,
    onClick: () -> Unit = {},
    onCreateParticipationClick: () -> Unit = {}
) {
    Card(
        shape = Shape10,
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(15.dp, 20.dp)) {
            Text(
                text = project.title,
                style = AppTheme.typography.title,
                color = AppTheme.colorScheme.textPrimary
            )
            if (project.supervisorsNames.isNotBlank()) {
                Text(
                    modifier = Modifier.padding(top = 7.dp),
                    text = project.supervisorsNames,
                    style = AppTheme.typography.bodyMedium.copy(
                        color = AppTheme.colorScheme.secondary
                    )
                )
            }
            if (project.specialities.isNotEmpty()) {
                Text(
                    text = project.specialities.joinToString { it.name },
                    style = AppTheme.typography.bodyMedium.copy(
                        color = AppTheme.colorScheme.secondary
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.People,
                            contentDescription = "People Icon",
                            tint = AppTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = project.places.toString(),
                            style = AppTheme.typography.bodyMedium.copy(
                                color = AppTheme.colorScheme.primary
                            )
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.Star,
                            contentDescription = "Star Icon",
                            tint = AppTheme.colorScheme.textPrimary
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = project.difficulty.toProjectDifficulty(),
                            style = AppTheme.typography.bodyMedium,
                            color = AppTheme.colorScheme.textPrimary
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .border(
                            BorderStroke(
                                width = 1.45.dp,
                                when (project.state.id) {
                                    1 -> com.istu.schedule.ui.theme.Blue
                                    2 -> com.istu.schedule.ui.theme.Green
                                    3 -> com.istu.schedule.ui.theme.Orange
                                    5 -> com.istu.schedule.ui.theme.Cyan
                                    else -> AppTheme.colorScheme.secondary
                                }
                            ),
                            RoundedCornerShape(72.dp)
                        )
                        .padding(24.dp, 7.dp)
                ) {
                    Text(
                        text = project.state.state.uppercase(),
                        style = AppTheme.typography.bodySmall.copy(
                            color = when (project.state.id) {
                                1 -> com.istu.schedule.ui.theme.Blue
                                2 -> com.istu.schedule.ui.theme.Green
                                5 -> com.istu.schedule.ui.theme.Cyan
                                else -> AppTheme.colorScheme.secondary
                            },
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = HalfGray
            )
            if (project.goal.isNotBlank()) {
                Text(
                    modifier = Modifier.padding(top = 15.dp),
                    text = buildAnnotatedString {
                        append(
                            AnnotatedString(
                                text = stringResource(id = R.string.aim_project),
                                spanStyle = SpanStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = AppTheme.typography.bodyMedium.fontFamily,
                                    fontSize = AppTheme.typography.bodyMedium.fontSize,
                                    fontStyle = AppTheme.typography.bodyMedium.fontStyle,
                                    color = AppTheme.colorScheme.textPrimary
                                )
                            )
                        )
                        append(
                            AnnotatedString(
                                text = project.goal,
                                spanStyle = SpanStyle(
                                    fontFamily = AppTheme.typography.bodyMedium.fontFamily,
                                    fontSize = AppTheme.typography.bodyMedium.fontSize,
                                    fontStyle = AppTheme.typography.bodyMedium.fontStyle,
                                    color = AppTheme.colorScheme.secondary
                                )
                            )
                        )
                    },
                    lineHeight = 16.sp
                )
            }
            Text(
                modifier = Modifier.padding(top = 17.dp),
                text = buildAnnotatedString {
                    append(
                        AnnotatedString(
                            text = stringResource(id = R.string.start_date),
                            spanStyle = SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = AppTheme.typography.bodyMedium.fontFamily,
                                fontSize = AppTheme.typography.bodyMedium.fontSize,
                                fontStyle = AppTheme.typography.bodyMedium.fontStyle,
                                color = AppTheme.colorScheme.textPrimary
                            )
                        )
                    )
                    append(
                        AnnotatedString(
                            text = DateFormat.getDateInstance(DateFormat.LONG)
                                .format(project.dateStart),
                            spanStyle = SpanStyle(
                                fontFamily = AppTheme.typography.bodyMedium.fontFamily,
                                fontSize = AppTheme.typography.bodyMedium.fontSize,
                                fontStyle = AppTheme.typography.bodyMedium.fontStyle,
                                color = AppTheme.colorScheme.secondary
                            )
                        )
                    )
                },
                lineHeight = 16.sp
            )
            if (project.skills.isNotEmpty()) {
                ChipVerticalGrid(
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .height(24.dp),
                    spacing = 7.dp,
                    moreItemsView = {
                        SITextChip(
                            text = pluralStringResource(
                                id = R.plurals.tags_left,
                                count = it,
                                it
                            )
                        )
                    }
                ) {
                    project.skills.sortedBy { it.name.length }.forEach {
                        SIChip(text = it.name)
                    }
                }
            }
            FilledButton(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .height(42.dp),
                text = stringResource(R.string.read_more),
                onClick = onClick
            )
            if (project.state.id == 1 && canCreateParticipation) {
                OutlineButton(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth()
                        .height(42.dp),
                    text = stringResource(R.string.send_participation),
                    onClick = onCreateParticipationClick
                )
            }
        }
    }
}

@Composable
fun ProjectItemPlaceHolder() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .placeholder(
                visible = true,
                color = AppTheme.colorScheme.textPrimary.copy(alpha = 0.05f),
                shape = Shape10,
                highlight = PlaceholderHighlight.fade(
                    highlightColor = AppTheme.colorScheme.textPrimary.copy(alpha = 0.08f)
                )
            )
    )
}

@Preview
@Composable
fun PreviewProjectItem(@PreviewParameter(SampleProjectProvider::class) project: Project) {
    AppTheme {
        ProjectItem(project = project)
    }
}
