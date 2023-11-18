package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.data.enums.ScheduleType
import com.istu.schedule.data.model.Week
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.ui.components.base.button.FilledButton
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.ShapeTop15
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun ScheduleContent(
    subtitle: String?,
    calendarState: LazyListState,
    weeksList: List<Week>,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    onSearchButtonClick: () -> Unit,
    onDateSelect: (LocalDate) -> Unit,
    backNavigationButton: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = {
            ScheduleTopBar(
                subtitle = subtitle,
                calendarState = calendarState,
                weeksList = weeksList,
                currentDate = currentDate,
                selectedDate = selectedDate,
                onSearchButtonClick = onSearchButtonClick,
                onDateSelect = onDateSelect,
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .clip(ShapeTop15)
                    .background(AppTheme.colorScheme.backgroundSecondary),
            ) {
                backNavigationButton?.invoke()
                content()
            }
        }
    )
}

@Composable
fun ScheduleList(
    currentDateTime: LocalDateTime,
    studyDay: StudyDay,
    onDropdownItemClick: (ScheduleType, Int, String) -> Unit,
    onLongPressVibrate: (() -> Unit)? = null,
) {
    val listState = rememberLazyListState()
    var pressOffset by remember { mutableStateOf(DpOffset.Zero) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        state = listState,
        contentPadding = PaddingValues(top = 15.dp, bottom = 30.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        studyDay.lessons.indices.forEach { index ->
            studyDay.lessons[index].also { lesson ->
                item {
                    ScheduleCard(
                        currentDateTime = currentDateTime,
                        lesson = lesson,
                        lessonDate = studyDay.date,
                        pressOffset = pressOffset,
                        onDropdownItemClick = { scheduleType, id, title ->
                            onDropdownItemClick(scheduleType, id, title)
                        },
                        onLongPress = {
                            pressOffset = it
                            onLongPressVibrate?.invoke()
                        },
                    )
                }
                lesson.breakTimeAfter?.let {
                    if (index < studyDay.lessons.lastIndex) {
                        item {
                            BreakTime(stringBreakTime = it)
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}

@Composable
fun ScheduleListIsLoading() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
        contentPadding = PaddingValues(top = 15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        items(3) {
            ScheduleCardPlaceHolder()
        }
        item {
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}

@Composable
fun UserNotBindPlaceholder(
    onSetupScheduleClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            space = 25.dp,
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.width(300.dp),
            text = stringResource(R.string.unknown_user),
            style = AppTheme.typography.title.copy(color = AppTheme.colorScheme.textPrimary),
            textAlign = TextAlign.Center,
        )
        FilledButton(
            modifier = Modifier
                .width(250.dp),
            text = stringResource(R.string.setup_schedule),
            onClick = onSetupScheduleClick,
        )
        Spacer(modifier = Modifier.height(64.dp))
    }
}

@Composable
fun WeekendPlaceholder(
    currentDate: LocalDate,
    selectedDate: LocalDate,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 15.dp,
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(180.dp),
            painter = painterResource(R.drawable.login_to_personal_account),
            contentDescription = null,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = when (selectedDate) {
                currentDate -> stringResource(R.string.weekend_today)
                currentDate.plusDays(1) -> stringResource(R.string.weekend_tomorrow)
                else -> stringResource(R.string.weekend)
            },
            style = AppTheme.typography.subtitle,
            color = AppTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(64.dp))
    }
}
