package com.istu.schedule.ui.page.settings.schedule

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.domain.entities.schedule.Course
import com.istu.schedule.domain.entities.schedule.Group
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.ShapeTop15

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
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = {
            TopBar(
                title = stringResource(id = R.string.setting_schedule),
                isShowBackButton = true,
                onBackPressed = { onBackClick() }
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .clip(ShapeTop15)
                    .background(AppTheme.colorScheme.backgroundSecondary)
                    .padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                contentPadding = PaddingValues(top = 20.dp, bottom = 30.dp)
            ) {
                item {
                    Text(
                        modifier = Modifier.padding(bottom = 15.dp),
                        text = instituteTitle,
                        style = AppTheme.typography.title.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = AppTheme.colorScheme.primary
                        )
                    )
                }
                items(courseList) { course ->
                    CourseAccordionItem(
                        course = course,
                        onChooseGroup = { group -> onChooseGroup(group) }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(64.dp))
                    Spacer(
                        modifier = Modifier.windowInsetsBottomHeight(
                            WindowInsets.navigationBars
                        )
                    )
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
