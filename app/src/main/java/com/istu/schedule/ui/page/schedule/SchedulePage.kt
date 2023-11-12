package com.istu.schedule.ui.page.schedule

/*
@Deprecated("will be removed")
@Composable
fun SchedulePage(
    studyDay: StudyDay?,
    weeksList: List<Week>,
    currentDateTime: LocalDateTime,
    selectedDate: LocalDate,
    onSearchButtonClick: () -> Unit,
    onSetupScheduleClick: () -> Unit,
    onDateSelect: (LocalDate) -> Unit,
    backNavigation: (@Composable () -> Unit)? = null,
) {
    Scaffold(
        containerColor = AppTheme.colorScheme.backgroundPrimary,
        topBar = {
            ScheduleTopBar(
                subtitle = scheduleUiState.description,
                calendarState = ,
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
                backNavigation = backNavigation,
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
                                        WeekendPlaceholder(
                                            currentDate = currentDateTime.toLocalDate(),
                                            selectedDate = selectedDate,
                                            spacer = {
                                                Spacer(modifier = Modifier.height(64.dp))
                                                Spacer(
                                                    modifier = Modifier.windowInsetsBottomHeight(
                                                        WindowInsets.navigationBars
                                                    )
                                                )
                                            }
                                        )
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
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun SchedulePageLoadingPreview() {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleOldUiState(
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
            networkStatus = NetworkStatus.Available
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageUnknownUserPreview() {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleOldUiState(),
            isLoading = false,
            studyDay = null,
            weeksList = listOf(Week(LocalDate.of(2023, 5, 29))),
            currentDateTime = LocalDateTime.of(2023, 6, 1, 12, 30),
            selectedDate = LocalDate.of(2023, 6, 3),
            onSearchButtonClick = { },
            onDateSelect = { },
            onSetupScheduleClick = { },
            networkStatus = NetworkStatus.Available
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageNoNetworkPreview() {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleOldUiState(),
            isLoading = false,
            studyDay = null,
            weeksList = listOf(Week(LocalDate.of(2023, 5, 29))),
            currentDateTime = LocalDateTime.of(2023, 6, 1, 12, 30),
            selectedDate = LocalDate.of(2023, 6, 3),
            onSearchButtonClick = { },
            onDateSelect = { },
            onSetupScheduleClick = { },
            networkStatus = NetworkStatus.Unavailable
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SchedulePageWeekendPreview() {
    AppTheme {
        SchedulePage(
            scheduleUiState = ScheduleOldUiState(
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
            scheduleUiState = ScheduleOldUiState(
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
            networkStatus = NetworkStatus.Available
        )
    }
}

 */
