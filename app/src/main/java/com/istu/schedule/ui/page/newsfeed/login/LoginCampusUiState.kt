package com.istu.schedule.ui.page.newsfeed.login

sealed class LoginCampusUiState {

    data object Unauthorized : LoginCampusUiState()
    data object Loading : LoginCampusUiState()
    data object Success : LoginCampusUiState()
}
