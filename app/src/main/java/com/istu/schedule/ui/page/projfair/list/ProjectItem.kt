package com.istu.schedule.ui.page.projfair.list

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.istu.schedule.R
import com.istu.schedule.domain.model.projfair.Project
import com.istu.schedule.ui.components.base.FilledButton
import com.istu.schedule.ui.components.base.OutlineButton
import com.istu.schedule.ui.icons.People
import com.istu.schedule.ui.icons.Star
import com.istu.schedule.ui.theme.HalfGray
import java.text.DateFormat

@Composable
fun ProjectItem(
    modifier: Modifier = Modifier,
    project: Project,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(15.dp, 7.5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.background)
            .clickable(onClick = onClick),
    ) {
        Column(modifier = Modifier.padding(15.dp, 20.dp)) {
            Text(
                text = project.title,
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
            )
            if (project.supervisorsNames.isNotBlank()) {
                Text(
                    modifier = Modifier.padding(top = 7.dp),
                    text = project.supervisorsNames,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.secondary,
                    ),
                )
            }
            if (project.specialities.isNotEmpty()) {
                Text(
                    text = project.specialities.joinToString { it.name },
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.secondary,
                    ),
                )
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.People,
                            contentDescription = "People Icon",
                            tint = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = project.places.toString(),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.primary,
                            ),
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.Star,
                            contentDescription = "Star Icon",
                            tint = MaterialTheme.colorScheme.tertiary,
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = project.difficulty.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .border(
                            BorderStroke(width = 1.45.dp, MaterialTheme.colorScheme.primary),
                            RoundedCornerShape(72.dp),
                        )
                        .padding(24.dp, 7.dp),
                ) {
                    Text(
                        text = project.state.state.uppercase(),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.primary,
                        ),
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = HalfGray,
            )
            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = buildAnnotatedString {
                    append(
                        AnnotatedString(
                            text = stringResource(id = R.string.aim_project),
                            spanStyle = SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
                            ),
                        ),
                    )
                    append(
                        AnnotatedString(
                            text = project.goal,
                            spanStyle = SpanStyle(
                                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
                                color = MaterialTheme.colorScheme.secondary,
                            ),
                        ),
                    )
                },
            )
            Text(
                modifier = Modifier.padding(top = 17.dp),
                text = buildAnnotatedString {
                    append(
                        AnnotatedString(
                            text = stringResource(id = R.string.start_date),
                            spanStyle = SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
                            ),
                        ),
                    )
                    append(
                        AnnotatedString(
                            text = DateFormat.getDateInstance(DateFormat.LONG).format(project.dateStart),
                            spanStyle = SpanStyle(
                                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
                                color = MaterialTheme.colorScheme.secondary,
                            ),
                        ),
                    )
                },
            )
            FilledButton(
                modifier = Modifier.padding(top = 15.dp).fillMaxWidth().height(42.dp),
                text = stringResource(R.string.read_more),
            )
            OutlineButton(
                modifier = Modifier.padding(top = 5.dp).fillMaxWidth().height(42.dp),
                text = stringResource(R.string.apply_participation),
            )
        }
    }
}
