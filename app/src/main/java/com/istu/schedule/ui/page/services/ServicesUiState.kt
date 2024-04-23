package com.istu.schedule.ui.page.services

sealed class ServicesUiState {

    data object Loading : ServicesUiState()
    data object Error : ServicesUiState()
    data object NetworkUnavailable : ServicesUiState()
    data object Unauthorized : ServicesUiState()
    data class Content(
        val data: String
    ) : ServicesUiState()
}
