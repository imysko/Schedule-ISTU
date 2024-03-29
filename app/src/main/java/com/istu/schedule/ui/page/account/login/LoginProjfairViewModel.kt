package com.istu.schedule.ui.page.account.login

import androidx.lifecycle.ViewModel
import com.istu.schedule.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginProjfairViewModel @Inject constructor(
    private val _user: User
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
        _user.loginProjfair(token)
    }
}
