package com.istu.schedule.ui.page.newsfeed.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.progneo.campus.data.preference.AuthStateManager
import me.progneo.campus.data.preference.AuthTokenManager
import me.progneo.campus.data.preference.RefreshTokenManager
import me.progneo.campus.domain.usecase.GetTokenUseCase
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

@HiltViewModel
class LoginCampusViewModel @Inject constructor(
    private val authStateManager: AuthStateManager,
    private val authTokenManager: AuthTokenManager,
    private val refreshTokenManager: RefreshTokenManager,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {

    fun login(url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val httpUrl = url.toHttpUrlOrNull()
            val code = httpUrl?.queryParameter("code")

            if (code != null) {
                val response = getTokenUseCase(code)
                response.onSuccess { tokenResponse ->
                    authStateManager.save(true)
                    authTokenManager.save(tokenResponse.accessToken)
                    refreshTokenManager.save(tokenResponse.refreshToken)
                }
            }
        }
    }
}
