package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.istu.schedule.ui.components.base.AppComposable
import com.istu.schedule.ui.components.base.SIScaffold
import java.time.LocalDateTime

@Composable
fun SchedulePage(
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val scheduleList by viewModel.scheduleList.observeAsState(initial = emptyList())

    AppComposable(
        viewModel = viewModel,
        content = {
            SIScaffold (
                content = {
                    LazyColumn(
                        modifier = Modifier
                            .padding(horizontal = 15.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        if (scheduleList.any()) {
                            items(scheduleList.first().lessons) { lesson ->
                                val currentDateTime = LocalDateTime.now()

                                ScheduleCard(
                                    currentDateTime = currentDateTime,
                                    lesson = lesson,
                                    lessonDate = scheduleList.first().date
                                )
                            }
                            item {
                                Spacer(modifier = Modifier.height(128.dp))
                                Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
                            }
                        }
                    }
                }
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun SchedulePagePreview() {
    SchedulePage()
}