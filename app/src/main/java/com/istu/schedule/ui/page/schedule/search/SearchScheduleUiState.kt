package com.istu.schedule.ui.page.schedule.search

sealed class SearchScheduleUiState {

    data object SearchResult : SearchScheduleUiState()
    data object EmptyBlank : SearchScheduleUiState()
    data object NoInternetConnection : SearchScheduleUiState()
}
