package com.istu.schedule.ui.page.main

sealed class MainUiState {

    data object HasNotification : MainUiState()
    data object Clear : MainUiState()
}
