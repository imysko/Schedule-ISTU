package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.domain.model.schedule.Classroom
import com.istu.schedule.domain.model.schedule.Group
import com.istu.schedule.domain.model.schedule.Teacher
import com.istu.schedule.ui.theme.AppTheme
import com.istu.schedule.ui.theme.Shape5
import com.istu.schedule.ui.theme.ShapeTop15

@Composable
fun SearchContent(
    paddingValues: PaddingValues = PaddingValues(),
    isFoundedListsVisible: Boolean,
    searchedListsTips: SearchedLists,
    onSearchButtonClick: () -> Unit
) {
    val titleGroups = stringResource(id = R.string.groups)
    val titleTeachers = stringResource(id = R.string.teachers)
    val titleClassrooms = stringResource(id = R.string.classrooms)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .clip(ShapeTop15)
            .background(AppTheme.colorScheme.background)
    ) {
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
@Preview(name = "Not visible founded lists")
fun SearchContentNotVisibleFoundedListsPreview() {
    AppTheme {
        SearchContent(
            isFoundedListsVisible = false,
            searchedListsTips = SearchedLists(),
            onSearchButtonClick = { }
        )
    }
}

@Composable
@Preview(name = "Not found")
fun SearchContentNotFoundPreview() {
    AppTheme {
        SearchContent(
            isFoundedListsVisible = true,
            searchedListsTips = SearchedLists(),
            onSearchButtonClick = { }
        )
    }
}

@Composable
@Preview
fun SearchContentPreview() {
    AppTheme {
        SearchContent(
            isFoundedListsVisible = true,
            searchedListsTips = SearchedLists(
                groupList = listOf(
                    Group(
                        groupId = 0,
                        name = "ИСТб-20-1",
                        course = 3,
                        instituteId = null,
                        institute = null
                    ),
                    Group(
                        groupId = 0,
                        name = "ИСТб-20-2",
                        course = 3,
                        instituteId = null,
                        institute = null
                    ),
                    Group(
                        groupId = 0,
                        name = "ИСТб-20-3",
                        course = 3,
                        instituteId = null,
                        institute = null
                    )
                ),
                classroomList = listOf(
                    Classroom(
                        classroomId = 0,
                        name = "В-107"
                    )
                )
            ),
            onSearchButtonClick = { }
        )
    }
}
