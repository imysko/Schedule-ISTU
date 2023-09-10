package com.istu.schedule.ui.page.schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.istu.schedule.data.enums.NetworkStatus
import com.istu.schedule.data.model.Week
import com.istu.schedule.domain.model.schedule.SampleStudyDayProvider
import com.istu.schedule.domain.model.schedule.StudyDay
import com.istu.schedule.ui.components.base.NoInternetPanel
import com.istu.schedule.ui.components.base.SIAnimatedVisibilityFadeOnly
import com.istu.schedule.ui.theme.AppTheme
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun SchedulePage(
    scheduleUiState: ScheduleUiState,
    isLoading: Boolean,
    studyDay: StudyDay?,
    weeksList: List<Week>,
    currentDateTime: LocalDateTime,
    selectedDate: LocalDate,
    networkStatus: NetworkStatus,
    isShowBackButton: Boolean = false,
    onSearchButtonClick: () -> Unit,
    onSetupScheduleClick: () -> Unit,
    onDateSelect: (LocalDate) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = {
            ScheduleTopBar(
                scheduleUiState = scheduleUiState,
                weeksList = weeksList,
                currentDate = currentDateTime.toLocalDate(),
                selectedDate = selectedDate,
                onSearchButtonClick = { onSearchButtonClick() },
                onDateSelect = { onDateSelect(it) }
            )
        },
        content = {
            ScheduleContent(
                paddingValues = it,
                content = {
                    SIAnimatedVisibilityFadeOnly(!scheduleUiState.isShowSchedule) {
                        UserNotBindedPlaceholder { onSetupScheduleClick() }
                    }
                    SIAnimatedVisibilityFadeOnly(scheduleUiState.isShowSchedule) {
                        Box {
                            SIAnimatedVisibilityFadeOnly(
                                networkStatus == NetworkStatus.Unavailable && !isLoading
                            ) {
                                NoInternetPanel()
                            }

                            SIAnimatedVisibilityFadeOnly(
                                networkStatus == NetworkStatus.Available && isLoading
                            ) {
                                ScheduleListIsLoading(spacer = {
                                    Spacer(modifier = Modifier.height(64.dp))
                                    Spacer(
                                        modifier = Modifier.windowInsetsBottomHeight(
                                            WindowInsets.navigationBars
                                        )
                                    )
                                })
                            }

                            SIAnimatedVisibilityFadeOnly(
                                networkStatus == NetworkStatus.Available && !isLoading
                            ) {
                                studyDay?.let { studyDay ->
                                    if (studyDay.lessons.isEmpty()) {
                                        WeekendPlaceholder(spacer = {
                                            Spacer(modifier = Modifier.height(64.dp))
                                            Spacer(
                                                modifier = Modifier.windowInsetsBottomHeight(
                                                    WindowInsets.navigationBars
                                                )
                                            )
                                        })
                                    } else {
                                        ScheduleList(
                                            currentDateTime = currentDateTime,
                                            studyDay = studyDay,
                                            spacer = {
                                                Spacer(modifier = Modifier.height(64.dp))
                                                Spacer(
                                                    modifier = Modifier.windowInsetsBottomHeight(
                                                        WindowInsets.navigationBars
                                                    )
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                },
                isShowBackButton = isShowBackButton,
                onBackClick = { onBackClick() }
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun SchedulePageLoadingPreview() {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleUiState(
                isShowDescription = true,
                description = "ИСТб-20-3"
            ),
            isLoading = true,
            studyDay = null,
            weeksList = listOf(
                Week(LocalDate.of(2023, 5, 29))
            ),
            currentDateTime = LocalDateTime.of(2023, 6, 1, 12, 30),
            selectedDate = LocalDate.of(2023, 6, 3),
            onSearchButtonClick = { },
            onDateSelect = { },
            onSetupScheduleClick = { },
            onBackClick = { },
            networkStatus = NetworkStatus.Available
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
            studyDay = null,
            weeksList = listOf(Week(LocalDate.of(2023, 5, 29))),
            currentDateTime = LocalDateTime.of(2023, 6, 1, 12, 30),
            selectedDate = LocalDate.of(2023, 6, 3),
            onSearchButtonClick = { },
            onDateSelect = { },
            onSetupScheduleClick = { },
            onBackClick = { },
            networkStatus = NetworkStatus.Available
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageNoNetworkPreview() {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleUiState(),
            isLoading = false,
            studyDay = null,
            weeksList = listOf(Week(LocalDate.of(2023, 5, 29))),
            currentDateTime = LocalDateTime.of(2023, 6, 1, 12, 30),
            selectedDate = LocalDate.of(2023, 6, 3),
            onSearchButtonClick = { },
            onDateSelect = { },
            onSetupScheduleClick = { },
            onBackClick = { },
            networkStatus = NetworkStatus.Unavailable
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageWeekendPreview() {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleUiState(
                isShowDescription = true,
                description = "ИСТб-20-3"
            ),
            isLoading = false,
            studyDay = StudyDay(
                date = "",
                lessons = emptyList()
            ),
            weeksList = listOf(
                Week(LocalDate.of(2023, 5, 29))
            ),
            currentDateTime = LocalDateTime.of(2023, 6, 1, 12, 30),
            selectedDate = LocalDate.of(2023, 6, 3),
            onSearchButtonClick = { },
            onDateSelect = { },
            onSetupScheduleClick = { },
            onBackClick = { },
            networkStatus = NetworkStatus.Available
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageScheduleList(
    @PreviewParameter(SampleStudyDayProvider::class) studyDay: StudyDay
) {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleUiState(
                isShowDescription = true,
                description = "ИСТб-20-3"
            ),
            isLoading = false,
            studyDay = studyDay,
            weeksList = listOf(
                Week(LocalDate.of(2023, 5, 29))
            ),
            currentDateTime = LocalDateTime.of(2023, 6, 1, 12, 30),
            selectedDate = LocalDate.of(2023, 6, 3),
            onSearchButtonClick = { },
            onDateSelect = { },
            onSetupScheduleClick = { },
            onBackClick = { },
            networkStatus = NetworkStatus.Available
        )
    }
}
