package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.domain.model.schedule.SampleStudyDayProvider
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.ui.components.base.button.FilledButton
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.theme.ShapeTop15
import java.time.LocalDateTime

@Composable
fun ScheduleContent(
    paddingValues: PaddingValues = PaddingValues(),
    content: @Composable () -> Unit = { },
    isShowBackButton: Boolean = false,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .clip(ShapeTop15)
            .background(AppTheme.colorScheme.backgroundSecondary),
        verticalArrangement = Arrangement.Center
    ) {
        if (isShowBackButton) {
            Box(
                modifier = Modifier.padding(top = 13.dp, start = 15.dp, end = 15.dp)
            ) {
                Row(
                    modifier = Modifier
                        .clip(Shape10)
                        .clickable(onClick = { onBackClick() })
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(9.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = stringResource(R.string.back_to_schedule_search),
                        tint = AppTheme.colorScheme.secondary
                    )
                    Text(
                        text = stringResource(R.string.back_to_schedule_search),
                        style = AppTheme.typography.bodyMedium.copy(
                            color = AppTheme.colorScheme.secondary,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
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
        contentPadding = PaddingValues(vertical = 20.dp),
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
fun UserNotBindedPlaceholder(onSetupScheduleClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(ShapeTop15)
            .background(AppTheme.colorScheme.backgroundSecondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.width(300.dp),
            text = stringResource(R.string.unknown_user),
            style = AppTheme.typography.title.copy(color = AppTheme.colorScheme.textPrimary),
            textAlign = TextAlign.Center
        )
        FilledButton(
            modifier = Modifier.padding(top = 25.dp).width(250.dp),
            text = stringResource(R.string.setup_schedule),
            onClick = { onSetupScheduleClick() }
        )
    }
}

@Composable
fun WeekendPlaceholder(
    spacer: @Composable () -> Unit = { }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 15.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(180.dp),
            painter = painterResource(R.drawable.login_to_personal_account),
            contentDescription = null
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.weekend),
            style = AppTheme.typography.subtitle,
            color = AppTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
        spacer()
    }
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
            },
            isShowBackButton = true,
            onBackClick = { }
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
            },
            onBackClick = { }
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
            },
            onBackClick = { }
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
            },
            onBackClick = { }
        )
    }
}
