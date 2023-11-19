package com.istu.schedule.ui.page.schedule.found

sealed class FoundScheduleUiState {
    data object NoInternetConnection : FoundScheduleUiState()

    data object Weekend : FoundScheduleUiState()

    data object Schedule : FoundScheduleUiState()

    data class Error(
        val message: String,
    ) : FoundScheduleUiState()

    data object OnLoading : FoundScheduleUiState()
}