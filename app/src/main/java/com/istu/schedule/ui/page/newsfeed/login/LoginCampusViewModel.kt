package com.istu.schedule.ui.page.newsfeed.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.progneo.campus.data.preference.CampusAuthStateManager
import me.progneo.campus.data.preference.RefreshTokenManager
import me.progneo.campus.data.preference.UserIdManager
import me.progneo.campus.domain.usecase.GetCurrentUserUseCase
import me.progneo.campus.domain.usecase.GetTokenUseCase
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

@HiltViewModel
class LoginCampusViewModel @Inject constructor(
    private val campusAuthStateManager: CampusAuthStateManager,
    private val refreshTokenManager: RefreshTokenManager,
    private val userIdManager: UserIdManager,
    private val getTokenUseCase: GetTokenUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginCampusUiState>(LoginCampusUiState.Unauthorized)
    val uiState = _uiState.asStateFlow()

    fun login(url: String) {
        _uiState.tryEmit(LoginCampusUiState.Loading)
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                val httpUrl = url.toHttpUrlOrNull()
                val code = httpUrl?.queryParameter("code")

                if (code != null) {
                    val response = getTokenUseCase(code)
                    response.onSuccess { tokenResponse ->
                        campusAuthStateManager.save(true)
                        refreshTokenManager.save(tokenResponse.refreshToken)
                    }
                    val userResponse = getCurrentUserUseCase()
                    userResponse.onSuccess { user ->
                        userIdManager.save(user.id)
                    }
                    _uiState.tryEmit(LoginCampusUiState.Success)
                }
            }
        }
    }
}
