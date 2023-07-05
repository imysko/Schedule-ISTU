package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.istu.schedule.R
import com.istu.schedule.data.model.Week
import com.istu.schedule.domain.model.schedule.Classroom
import com.istu.schedule.domain.model.schedule.Group
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.domain.model.schedule.Teacher
import com.istu.schedule.ui.components.base.SIExtensibleVisibility
import com.istu.schedule.ui.components.base.SearchBar
import com.istu.schedule.ui.components.base.button.FilledButton
import com.istu.schedule.ui.components.calendar.HorizontalCalendar
import com.istu.schedule.ui.fonts.interFamily
import com.istu.schedule.ui.icons.Search
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Green
import com.istu.schedule.ui.theme.GreenContainer
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.theme.Shape5
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.util.NavDestinations
import com.istu.schedule.util.collectAsStateValue
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun SchedulePage(
    navController: NavHostController,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val isLoading by viewModel.loading.observeAsState(initial = false)

    val scheduleUiState = viewModel.scheduleUiState.collectAsStateValue()

    val weeksList by viewModel.weeksList.observeAsState(initial = emptyList())
    val currentDate by viewModel.currentDate.observeAsState(initial = LocalDate.now())
    val selectedDate by viewModel.selectedDate.observeAsState(initial = LocalDate.now())

    val schedule by viewModel.schedule.observeAsState()

    val searchedListsTips = viewModel.searchedListsTips.collectAsStateValue()

    val startOfListReached by remember {
        derivedStateOf {
            scheduleUiState.calendarState.canScrollBackward
        }
    }

    val endOfListReached by remember {
        derivedStateOf {
            scheduleUiState.calendarState.canScrollForward
        }
    }

    LaunchedEffect(Unit) {
        scheduleUiState.calendarState.scrollToItem(1)
    }

    LaunchedEffect(navController) {
        viewModel.updateUserInformation()
    }

    LaunchedEffect(selectedDate) {
        viewModel.getSchedule()
    }

    LaunchedEffect(startOfListReached) {
        viewModel.addWeekForward()
        scheduleUiState.calendarState.scrollToItem(
            index = scheduleUiState.calendarState.firstVisibleItemIndex + 1
        )
    }

    LaunchedEffect(endOfListReached) {
        viewModel.addWeekBackward()
    }

    SchedulePage(
        scheduleUiState = scheduleUiState,
        isLoading = isLoading,
        schedule = schedule,
        weeksList = weeksList,
        currentDate = currentDate,
        selectedDate = selectedDate,
        searchedListsTips = searchedListsTips,
        onSearchButtonClick = { viewModel.changeSearchBarVisibility() },
        onDateSelect = { viewModel.selectDate(it) },
        onValueInputDone = { viewModel.onValueInput(it) },
        onSetupScheduleClick = { navController.navigate(NavDestinations.BINDING) }
    )
}

@Composable
fun SchedulePage(
    scheduleUiState: ScheduleUiState,
    isLoading: Boolean,
    schedule: StudyDay?,
    weeksList: List<Week>,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    searchedListsTips: SearchedLists,
    onSearchButtonClick: () -> Unit,
    onDateSelect: (LocalDate) -> Unit,
    onValueInputDone: (String) -> Unit,
    onSetupScheduleClick: () -> Unit
) {
    Scaffold(
        containerColor = AppTheme.colorScheme.primary,
        topBar = {
            ScheduleTopBar(
                scheduleUiState = scheduleUiState,
                weeksList = weeksList,
                currentDate = currentDate,
                selectedDate = selectedDate,
                onSearchButtonClick = { onSearchButtonClick() },
                onDateSelect = { onDateSelect(it) },
                onValueInputDone = { onValueInputDone(it) }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .clip(ShapeTop15)
                    .background(AppTheme.colorScheme.background)
            ) {
                SIExtensibleVisibility(scheduleUiState.isScheduleListVisible) {
                    ScheduleContent(
                        isUserBinded = scheduleUiState.isUserBinded,
                        isLoading = isLoading,
                        schedule = schedule,
                        onSetupScheduleClick = { onSetupScheduleClick() }
                    )
                }

                SIExtensibleVisibility(scheduleUiState.isSearchContentVisible) {
                    SearchContent(
                        isFoundedListsVisible = scheduleUiState.isFoundedListsVisible,
                        searchedListsTips = searchedListsTips,
                        onSearchButtonClick = { onSearchButtonClick() }
                    )
                }
            }
        }
    )
}

@Composable
fun ScheduleTopBar(
    scheduleUiState: ScheduleUiState,
    weeksList: List<Week>,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    onSearchButtonClick: () -> Unit,
    onDateSelect: (LocalDate) -> Unit,
    onValueInputDone: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    var value by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(vertical = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (scheduleUiState.isScheduleListVisible) {
                Text(
                    text = stringResource(id = R.string.title_schedule),
                    style = AppTheme.typography.pageTitle,
                    color = AppTheme.colorScheme.textSecondary
                )
            } else if (scheduleUiState.isSearchBarVisible) {
                Text(
                    text = stringResource(id = R.string.search_schedule),
                    style = AppTheme.typography.pageTitle,
                    color = AppTheme.colorScheme.textSecondary
                )
            }

            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = { onSearchButtonClick() },
                content = {
                    Icon(
                        imageVector = Icons.Search,
                        contentDescription = stringResource(id = R.string.search),
                        tint = AppTheme.colorScheme.textSecondary
                    )
                }
            )
        }

        if (scheduleUiState.isScheduleListVisible && scheduleUiState.isUserBinded) {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = scheduleUiState.userDescription!!,
                style = AppTheme.typography.title,
                color = AppTheme.colorScheme.textSecondary
            )
        }

        SIExtensibleVisibility(visible = scheduleUiState.isSearchBarVisible) {
            SearchBar(
                modifier = Modifier
                    .padding(top = 15.dp, end = 15.dp, start = 15.dp)
                    .height(42.dp),
                value = value,
                focusRequester = focusRequester,
                placeholder = stringResource(R.string.schedule_search_bar_label),
                onValueChange = { value = it },
                onDone = { onValueInputDone(value) }
            )
        }

        SIExtensibleVisibility(visible = scheduleUiState.isCalendarVisible) {
            HorizontalCalendar(
                weeksList = weeksList,
                currentDate = currentDate,
                selectedDate = selectedDate,
                calendarState = scheduleUiState.calendarState,
                onSelect = { onDateSelect(it) }
            )
        }
    }
}

@Composable
fun ScheduleContent(
    isUserBinded: Boolean,
    isLoading: Boolean,
    schedule: StudyDay?,
    onSetupScheduleClick: () -> Unit
) {
    if (!isUserBinded) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.unknown_user),
                style = AppTheme.typography.subtitle.copy(
                    color = AppTheme.colorScheme.textPrimary
                )
            )
            FilledButton(
                modifier = Modifier
                    .padding(top = 25.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.setup_schedule),
                onClick = { onSetupScheduleClick() }
            )
            Spacer(modifier = Modifier.height(128.dp))
            Spacer(
                modifier = Modifier.windowInsetsBottomHeight(
                    WindowInsets.navigationBars
                )
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            contentPadding = PaddingValues(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            schedule?.let {
                if (isLoading) {
                    items(3) {
                        ScheduleCardPlaceHolder()
                    }
                }
                if (schedule.lessons.isEmpty()) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
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
                            Spacer(modifier = Modifier.height(128.dp))
                            Spacer(
                                modifier = Modifier.windowInsetsBottomHeight(
                                    WindowInsets.navigationBars
                                )
                            )
                        }
                    }
                }
                schedule.lessons.forEach { lesson ->
                    item {
                        val currentDateTime = LocalDateTime.now()

                        ScheduleCard(
                            currentDateTime = currentDateTime,
                            lesson = lesson,
                            lessonDate = schedule.date
                        )
                    }
                    lesson.breakTimeAfter?.let {
                        item {
                            BreakTime(stringBreakTime = it)
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(128.dp))
                Spacer(
                    modifier = Modifier.windowInsetsBottomHeight(
                        WindowInsets.navigationBars
                    )
                )
            }
        }
    }
}

@Composable
fun SearchContent(
    isFoundedListsVisible: Boolean,
    searchedListsTips: SearchedLists,
    onSearchButtonClick: () -> Unit
) {
    val titleGroups = stringResource(id = R.string.groups)
    val titleTeachers = stringResource(id = R.string.teachers)
    val titleClassrooms = stringResource(id = R.string.classrooms)

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxSize(),
        contentPadding = PaddingValues(top = 23.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        item {
            Box(
                modifier = Modifier.clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = { onSearchButtonClick() }
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(9.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = stringResource(R.string.back_to_your_schedule),
                        tint = AppTheme.colorScheme.secondary
                    )
                    Text(
                        text = stringResource(R.string.back_to_your_schedule),
                        style = AppTheme.typography.bodyMedium.copy(
                            color = AppTheme.colorScheme.secondary,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
        if (isFoundedListsVisible) {
            foundedList(title = titleGroups, list = searchedListsTips.groupList)
            foundedList(title = titleTeachers, list = searchedListsTips.teacherList)
            foundedList(title = titleClassrooms, list = searchedListsTips.classroomList)
        }
        item {
            Spacer(modifier = Modifier.height(128.dp))
            Spacer(
                modifier = Modifier.windowInsetsBottomHeight(
                    WindowInsets.navigationBars
                )
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
internal fun LazyListScope.foundedList(
    title: String,
    list: List<Any> = emptyList()
) {
    list.also {
        stickyHeader {
            Text(
                modifier = Modifier
                    .background(AppTheme.colorScheme.background)
                    .fillMaxWidth(),
                text = title,
                style = AppTheme.typography.title
            )
        }
        item {
            Divider(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
            )
        }
        items(it) { item ->
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 20.dp)
                    .clip(Shape5)
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = when (item) {
                        is Group -> item.name!!
                        is Teacher -> item.fullName
                        is Classroom -> item.name
                        else -> ""
                    },
                    style = AppTheme.typography.title
                )
            }
        }
        if (list.isEmpty()) {
            item {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 20.dp),
                    text = stringResource(id = R.string.not_found),
                    style = AppTheme.typography.title
                )
            }
        }
    }
}

@Composable
fun BreakTime(stringBreakTime: String) {
    Box(
        modifier = Modifier
            .background(
                color = GreenContainer,
                shape = Shape10
            )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.break_time),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = interFamily,
                color = Green
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                if (LocalTime.parse(stringBreakTime).hour > 0) {
                    Text(
                        text = pluralStringResource(
                            id = R.plurals.hours,
                            count = LocalTime.parse(stringBreakTime).hour,
                            LocalTime.parse(stringBreakTime).hour
                        ),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = interFamily,
                        color = Green
                    )
                }
                if (LocalTime.parse(stringBreakTime).minute > 0) {
                    Text(
                        text = pluralStringResource(
                            id = R.plurals.minutes,
                            count = LocalTime.parse(stringBreakTime).minute,
                            LocalTime.parse(stringBreakTime).minute
                        ),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = interFamily,
                        color = Green
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, locale = "ru")
fun BreakTimePreview() {
    AppTheme {
        BreakTime(stringBreakTime = "02:30:00")
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageLoadingPreview() {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleUiState(),
            isLoading = true,
            schedule = null,
            weeksList = listOf(
                Week(LocalDate.of(2023, 6, 12))
            ),
            currentDate = LocalDate.now(),
            selectedDate = LocalDate.now().plusDays(2),
            searchedListsTips = SearchedLists(),
            onSearchButtonClick = { },
            onDateSelect = { },
            onValueInputDone = { },
            onSetupScheduleClick = { }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageUnknownUserPreview() {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleUiState(),
            isLoading = false,
            schedule = StudyDay(
                date = "",
                lessons = emptyList()
            ),
            weeksList = listOf(
                Week(LocalDate.of(2023, 6, 12))
            ),
            currentDate = LocalDate.now(),
            selectedDate = LocalDate.now().plusDays(2),
            searchedListsTips = SearchedLists(),
            onSearchButtonClick = { },
            onDateSelect = { },
            onValueInputDone = { },
            onSetupScheduleClick = { }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageWeekendPreview() {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleUiState(
                isUserBinded = true
            ),
            isLoading = false,
            schedule = StudyDay(
                date = "",
                lessons = emptyList()
            ),
            weeksList = listOf(
                Week(LocalDate.of(2023, 6, 12))
            ),
            currentDate = LocalDate.now(),
            selectedDate = LocalDate.now().plusDays(2),
            searchedListsTips = SearchedLists(),
            onSearchButtonClick = { },
            onDateSelect = { },
            onValueInputDone = { },
            onSetupScheduleClick = { }
        )
    }
}
