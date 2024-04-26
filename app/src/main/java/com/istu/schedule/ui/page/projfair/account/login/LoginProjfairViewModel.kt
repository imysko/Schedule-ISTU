package com.istu.schedule.ui.page.projfair.account.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.progneo.projfair.data.preference.ProjfairAccessTokenManager

@HiltViewModel
class LoginProjfairViewModel @Inject constructor(
    private val projfairAccessTokenManager: ProjfairAccessTokenManager
) : ViewModel() {
    fun login(cookies: String) {
        var token = ""
        val cookiesArray = cookies.split(';')
        for (cookie in cookiesArray) {
            if (cookie.contains("token")) {
                token = cookie
                break
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            projfairAccessTokenManager.save(token)
        }
    }
}
