package com.istu.schedule.ui.page.settings.binding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.domain.model.schedule.Course
import com.istu.schedule.domain.model.schedule.Group
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme

@Composable
fun ChooseGroup(
    instituteTitle: String,
    courseList: List<Course> = emptyList(),
    onBackClick: () -> Unit,
    onChooseGroup: (chosenGroup: Group) -> Unit
) {
    BackHandler {
        onBackClick()
    }

    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundSecondary,
        topBar = {
            TopBar(
                title = stringResource(id = R.string.account),
                onBackClick = { onBackClick() }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .background(AppTheme.colorScheme.backgroundSecondary)
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 19.dp, start = 15.dp, end = 15.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 2.dp),
                        text = instituteTitle,
                        style = AppTheme.typography.title.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = AppTheme.colorScheme.primary
                        )
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        courseList.forEach {
                            item {
                                CourseAccordionItem(
                                    course = it,
                                    onChooseGroup = { group -> onChooseGroup(group) }
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true, locale = "ru")
fun ChooseGroupPreview() {
    AppTheme {
        ChooseGroup(
            instituteTitle = "Институт Информационных Технологий и Анализа Данных",
            courseList = mutableListOf(
                Course(
                    courseNumber = 1,
                    groups = emptyList()
                ),
                Course(
                    courseNumber = 2,
                    groups = emptyList()
                ),
                Course(
                    courseNumber = 3,
                    groups = emptyList()
                ),
                Course(
                    courseNumber = 4,
                    groups = emptyList()
                )
            ),
            onBackClick = { },
            onChooseGroup = { }
        )
    }
}
