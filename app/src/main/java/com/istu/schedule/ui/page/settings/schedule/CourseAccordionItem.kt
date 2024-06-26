package com.istu.schedule.ui.page.settings.schedule

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.domain.entities.schedule.Course
import com.istu.schedule.domain.entities.schedule.Group
import com.istu.schedule.ui.components.base.SIAnimatedVisibilityFadeOnly
import com.istu.schedule.ui.components.base.TrailingIcon
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.util.previewParameterProviders.SampleGroupListPreviewParameterProvider
import java.util.Locale

@Composable
fun CourseAccordionItem(
    course: Course,
    expanded: Boolean = false,
    onChooseGroup: (chosenGroup: Group) -> Unit
) {
    var isExpanded by rememberSaveable { mutableStateOf(expanded) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = Shape10,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shape10)
                    .clickable { isExpanded = !isExpanded }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${course.courseNumber} ${
                            stringResource(id = R.string.course).lowercase(Locale.getDefault())
                        }",
                        style = AppTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = AppTheme.colorScheme.textPrimary
                    )

                    TrailingIcon(isExpanded)
                }
            }

            SIAnimatedVisibilityFadeOnly(visible = isExpanded) {
                FlowGroups(
                    groups = course.groups!!,
                    onChooseGroup = { onChooseGroup(it) }
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowGroups(
    groups: List<Group> = emptyList(),
    onChooseGroup: (chosenGroup: Group) -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        groups.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.48f)
                    .clip(Shape10)
                    .background(AppTheme.colorScheme.primaryContainer)
                    .clickable { onChooseGroup(it) },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = it.name!!,
                    style = AppTheme.typography.title,
                    color = AppTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
@Preview
fun FlowGroupsPreview(
    @PreviewParameter(SampleGroupListPreviewParameterProvider::class) groups: List<Group>
) {
    AppTheme {
        FlowGroups(
            groups = groups,
            onChooseGroup = { }
        )
    }
}

@Composable
@Preview(name = "Accordion item compressed", locale = "ru")
fun CourseAccordionItemPreview() {
    AppTheme {
        CourseAccordionItem(
            course = Course(
                courseNumber = 3,
                groups = emptyList()
            ),
            expanded = false,
            onChooseGroup = { }
        )
    }
}

@Composable
@Preview(name = "Accordion item expanded")
fun CourseAccordionItemExpandedPreview(
    @PreviewParameter(SampleGroupListPreviewParameterProvider::class) groups: List<Group>
) {
    AppTheme {
        CourseAccordionItem(
            course = Course(
                courseNumber = 3,
                groups = groups
            ),
            expanded = true,
            onChooseGroup = { }
        )
    }
}
