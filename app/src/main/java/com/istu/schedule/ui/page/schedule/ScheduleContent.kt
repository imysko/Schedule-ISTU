package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.domain.model.schedule.SampleStudyDayProvider
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.ui.components.base.button.FilledButton
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.ShapeTop15
import java.time.LocalDateTime

@Composable
fun ScheduleContent(
    paddingValues: PaddingValues = PaddingValues(),
    content: @Composable () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .clip(ShapeTop15)
            .background(AppTheme.colorScheme.backgroundSecondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Composable
fun ScheduleList(
    currentDateTime: LocalDateTime,
    studyDay: StudyDay,
    spacer: @Composable () -> Unit = { }
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        contentPadding = PaddingValues(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        studyDay.lessons.forEach { lesson ->
            item {
                ScheduleCard(
                    currentDateTime = currentDateTime,
                    lesson = lesson,
                    lessonDate = studyDay.date
                )
            }
            lesson.breakTimeAfter?.let {
                item {
                    BreakTime(stringBreakTime = it)
                }
            }
        }
        item {
            spacer()
        }
    }
}

@Composable
fun ScheduleListIsLoading(
    spacer: @Composable () -> Unit = { }
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        contentPadding = PaddingValues(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(3) {
            ScheduleCardPlaceHolder()
        }
        item {
            spacer()
        }
    }
}

@Composable
fun UserNotBindedPlaceholder(
    onSetupScheduleClick: () -> Unit,
    spacer: @Composable () -> Unit = { }
) {
    Text(
        modifier = Modifier.padding(horizontal = 15.dp),
        text = stringResource(R.string.unknown_user),
        style = AppTheme.typography.subtitle.copy(
            color = AppTheme.colorScheme.textPrimary
        )
    )
    FilledButton(
        modifier = Modifier
            .padding(top = 25.dp, start = 15.dp, end = 15.dp)
            .fillMaxWidth(),
        text = stringResource(R.string.setup_schedule),
        onClick = { onSetupScheduleClick() }
    )
    spacer()
}

@Composable
fun WeekendPlaceholder(
    spacer: @Composable () -> Unit = { }
) {
    Text(
        text = stringResource(id = R.string.weekend),
        style = AppTheme.typography.subtitle.copy(
            color = AppTheme.colorScheme.textPrimary
        )
    )
    Image(
        painter = painterResource(
            id = R.drawable.login_to_personal_account
        ),
        contentDescription = null
    )
    spacer()
}

@Composable
@Preview
fun ScheduleListPreview(
    @PreviewParameter(SampleStudyDayProvider::class) studyDay: StudyDay
) {
    val currentDateTime = LocalDateTime.of(2023, 6, 3, 13, 58)

    AppTheme {
        ScheduleContent(
            content = {
                ScheduleList(
                    currentDateTime = currentDateTime,
                    studyDay = studyDay
                )
            }
        )
    }
}

@Composable
@Preview
fun ScheduleListIsLoadingPreview() {
    AppTheme {
        ScheduleContent(
            content = {
                ScheduleListIsLoading()
            }
        )
    }
}

@Composable
@Preview
fun ScheduleContentUserNotBindedPlaceholderPreview() {
    AppTheme {
        ScheduleContent(
            content = {
                UserNotBindedPlaceholder(
                    onSetupScheduleClick = { }
                )
            }
        )
    }
}

@Composable
@Preview
fun ScheduleContentWeekendPlaceholderPreview() {
    AppTheme {
        ScheduleContent(
            content = {
                WeekendPlaceholder()
            }
        )
    }
}
