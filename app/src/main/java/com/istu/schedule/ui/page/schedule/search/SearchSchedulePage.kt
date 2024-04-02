package com.istu.schedule.ui.page.schedule.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.istu.schedule.R
import com.istu.schedule.domain.entities.schedule.Classroom
import com.istu.schedule.domain.entities.schedule.Group
import com.istu.schedule.domain.entities.schedule.ScheduleType
import com.istu.schedule.domain.entities.schedule.Teacher
import com.istu.schedule.ui.components.base.NoInternetPanel
import com.istu.schedule.ui.components.base.SIAnimatedVisibilityFadeOnly
import com.istu.schedule.ui.components.base.SearchBar
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape10
import com.istu.schedule.ui.theme.Shape5
import com.istu.schedule.ui.theme.ShapeTop15
import com.istu.schedule.ui.util.NavDestinations
import com.istu.schedule.util.collectAsStateValue

@Composable
fun SearchSchedulePage(
    navController: NavHostController,
    viewModel: SearchScheduleViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val searchedListsHints = viewModel.searchedListsHints.collectAsStateValue()

    SearchSchedulePage(
        uiState = uiState,
        searchedListsHints = searchedListsHints,
        onValueInputDone = { viewModel.onValueInput(it) },
        onHintClick = { scheduleType, id, title ->
            navController.navigate(
                "${NavDestinations.FOUND_SCHEDULE}/$scheduleType/$id/$title"
            )
        },
        onBackClick = { navController.popBackStack() }
    )
}

@Composable
fun SearchSchedulePage(
    uiState: SearchScheduleUiState,
    searchedListsHints: SearchedLists,
    onValueInputDone: (String) -> Unit,
    onHintClick: (ScheduleType, Int, String?) -> Unit,
    onBackClick: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    var value by remember { mutableStateOf("") }

    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = {
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
                    Text(
                        text = stringResource(id = R.string.search_schedule),
                        style = AppTheme.typography.pageTitle,
                        color = AppTheme.colorScheme.textSecondary
                    )
                }

                SearchBar(
                    modifier = Modifier
                        .padding(top = 15.dp, end = 15.dp, start = 15.dp)
                        .height(42.dp),
                    value = value,
                    placeholder = stringResource(R.string.schedule_search_bar_label),
                    focusRequester = focusRequester,
                    onValueChange = { value = it }
                ) { onValueInputDone(value) }
            }
        },
        content = {
            SearchContent(
                paddingValues = it,
                uiState = uiState,
                searchedListsHints = searchedListsHints,
                onHintClick = { scheduleType, id, title -> onHintClick(scheduleType, id, title) },
                onBackClick = { onBackClick() }
            )
        }
    )
}

@Composable
fun SearchContent(
    paddingValues: PaddingValues = PaddingValues(),
    uiState: SearchScheduleUiState,
    searchedListsHints: SearchedLists,
    onHintClick: (ScheduleType, Int, String) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .clip(ShapeTop15)
            .background(AppTheme.colorScheme.backgroundSecondary)
    ) {
        Box(modifier = Modifier.padding(vertical = 15.dp, horizontal = 15.dp)) {
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
        Box {
            when (uiState) {
                SearchScheduleUiState.NoInternetConnection -> {
                    SIAnimatedVisibilityFadeOnly(true) {
                        NoInternetPanel()
                    }
                }
                SearchScheduleUiState.SearchResult -> {
                    SIAnimatedVisibilityFadeOnly(true) {
                        val titleGroups = stringResource(id = R.string.groups)
                        val titleTeachers = stringResource(id = R.string.teachers)
                        val titleClassrooms = stringResource(id = R.string.classrooms)

                        LazyColumn(
                            modifier = Modifier
                                .padding(horizontal = 15.dp)
                                .fillMaxSize(),
                            contentPadding = PaddingValues(top = 15.dp),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            foundedList(
                                title = titleGroups,
                                list = searchedListsHints.groupsList,
                                onHintClick = { id, title ->
                                    onHintClick(ScheduleType.BY_GROUP, id, title)
                                }
                            )
                            foundedList(
                                title = titleTeachers,
                                list = searchedListsHints.teachersList,
                                onHintClick = { id, title ->
                                    onHintClick(ScheduleType.BY_TEACHER, id, title)
                                }
                            )
                            foundedList(
                                title = titleClassrooms,
                                list = searchedListsHints.classroomsList,
                                onHintClick = { id, title ->
                                    onHintClick(ScheduleType.BY_CLASSROOM, id, title)
                                }
                            )
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
                }
                SearchScheduleUiState.EmptyBlank -> Unit
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
internal fun LazyListScope.foundedList(
    title: String,
    list: List<Any> = emptyList(),
    onHintClick: (Int, String) -> Unit
) {
    stickyHeader {
        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Text(
                modifier = Modifier
                    .background(AppTheme.colorScheme.backgroundSecondary)
                    .fillMaxWidth(),
                text = title,
                style = AppTheme.typography.title
            )
            HorizontalDivider(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
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
    } else {
        items(list) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shape5)
                    .clickable {
                        when (item) {
                            is Group -> onHintClick(item.groupId, item.name ?: "")
                            is Teacher -> onHintClick(item.teacherId, item.fullName)
                            is Classroom -> onHintClick(item.classroomId, item.name)
                            else -> Unit
                        }
                    }
                    .padding(vertical = 8.dp, horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = when (item) {
                        is Group -> item.name ?: ""
                        is Teacher -> item.fullName
                        is Classroom -> item.name
                        else -> ""
                    },
                    style = AppTheme.typography.title
                )
            }
        }
    }
}

@Composable
@Preview(name = "Not visible founded lists")
fun SearchSchedulePageNotVisibleFoundedListsPreview() {
    AppTheme {
        SearchSchedulePage(
            uiState = SearchScheduleUiState.EmptyBlank,
            searchedListsHints = SearchedLists(),
            onValueInputDone = { },
            onHintClick = { _, _, _ -> },
            onBackClick = { }
        )
    }
}

@Composable
@Preview(name = "Not found")
fun SearchSchedulePageNotFoundPreview() {
    AppTheme {
        SearchSchedulePage(
            uiState = SearchScheduleUiState.SearchResult,
            searchedListsHints = SearchedLists(),
            onValueInputDone = { },
            onHintClick = { _, _, _ -> },
            onBackClick = { }
        )
    }
}

@Composable
@Preview
fun SearchSchedulePagePreview() {
    AppTheme {
        SearchSchedulePage(
            uiState = SearchScheduleUiState.SearchResult,
            searchedListsHints = SearchedLists(
                groupsList = listOf(
                    Group(
                        groupId = 0,
                        name = "ИСТб-20-1",
                        course = 3,
                        instituteId = null,
                        institute = null,
                        isActive = true
                    ),
                    Group(
                        groupId = 0,
                        name = "ИСТб-20-2",
                        course = 3,
                        instituteId = null,
                        institute = null,
                        isActive = true
                    ),
                    Group(
                        groupId = 0,
                        name = "ИСТб-20-3",
                        course = 3,
                        instituteId = null,
                        institute = null,
                        isActive = true
                    )
                ),
                classroomsList = listOf(
                    Classroom(
                        classroomId = 0,
                        name = "В-107"
                    )
                )
            ),
            onValueInputDone = { },
            onHintClick = { _, _, _ -> },
            onBackClick = { }
        )
    }
}

@Composable
@Preview(name = "No network connection")
fun SearchSchedulePageNoNetworkPreview() {
    AppTheme {
        SearchSchedulePage(
            uiState = SearchScheduleUiState.NoInternetConnection,
            searchedListsHints = SearchedLists(),
            onValueInputDone = { },
            onHintClick = { _, _, _ -> },
            onBackClick = { }
        )
    }
}
