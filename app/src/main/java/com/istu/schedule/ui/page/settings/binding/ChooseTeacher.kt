package com.istu.schedule.ui.page.settings.binding

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istu.schedule.R
import com.istu.schedule.domain.model.schedule.Teacher
import com.istu.schedule.ui.icons.Search
import com.istu.schedule.ui.page.settings.TopBar
import com.istu.schedule.ui.theme.ScheduleISTUTheme
import com.istu.schedule.ui.theme.Shape5

@Composable
fun ChooseTeacher(
    teachersList: List<Teacher> = emptyList(),
    onBackClick: () -> Unit,
    onChooseTeacher: (chosenTeacher: Teacher) -> Unit,
) {
    BackHandler {
        onBackClick()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopBar(
                title = stringResource(id = R.string.account),
                onBackClick = { onBackClick() }
            )
        },
        content = {
            /*
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                SearchLine(
                    modifier = Modifier.padding(top = 9.dp, start = 15.dp, end = 15.dp)
                )

                LazyColumn(
                    modifier = Modifier.padding(start = 25.dp, end = 15.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {

                }
            }
             */
        },
    )
}

@Composable
fun SearchLine(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clip(Shape5)
            .border(BorderStroke(2.dp, MaterialTheme.colorScheme.secondary)),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // TextField(
            //     value = "",
            //     onValueChange = { },
            // )

            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Search,
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = stringResource(id = R.string.search),
            )
        }
    }
}

@Composable
@Preview
fun SearchLinePreview() {
    ScheduleISTUTheme {
        SearchLine(Modifier)
    }
}

@Composable
@Preview(showBackground = true, locale = "ru")
fun ChooseTeacherPreview() {
    ScheduleISTUTheme {
        ChooseTeacher(
            onBackClick = { },
            onChooseTeacher = { },
        )
    }
}