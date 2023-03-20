package com.istu.schedule.data.model

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.enums.ProjfairAuthStatus
import com.istu.schedule.data.enums.UserStatus
import com.istu.schedule.domain.model.projfair.Candidate
import com.istu.schedule.domain.usecase.projfair.GetCandidateUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class User @Inject constructor(
    private val _sharedPreference: SharedPreferences,
    private val _candidateUseCase: GetCandidateUseCase
) {

    private val _candidate = MutableStateFlow<Candidate?>(null)
    val candidate: StateFlow<Candidate?> = _candidate

    private val _authStatus = MutableLiveData(ProjfairAuthStatus.UNDEFINED)
    val authStatus: LiveData<ProjfairAuthStatus> = _authStatus

    init {
        setAuth()
    }

    private fun setAuth() {
        if (projfairToken != null) {
            getCandidate()
        }
        else {
            _authStatus.value = ProjfairAuthStatus.AUTH
        }
    }

    private fun getCandidate() {
        MainScope().launch {
            _candidateUseCase.getCandidate(projfairToken!!)
                .onSuccess {
                    _authStatus.value = ProjfairAuthStatus.SUCCESS
                    _candidate.value = it
                }
                .onFailure {
                    _authStatus.value = ProjfairAuthStatus.AUTH
                    projfairToken = null
                }
        }
    }

    fun logoutProjfair() {
        projfairToken = null;
        _candidate.value = null;
    }

    var projfairToken: String?
        get() {
            return _sharedPreference.getString(PROJFAIR_TOKEN, null)
        }
        set(value) {
            with(_sharedPreference.edit()) {
                if (value == null) {
                    remove(PROJFAIR_TOKEN)
                }
                else {
                    putString(PROJFAIR_TOKEN, value)
                }
                apply()
                setAuth()
            }
        }

    var userType: UserStatus?
        get() {
            return _sharedPreference.getString(USER_TYPE, null).toUserStatusEnum()
        }
        set(value) {
            with(_sharedPreference.edit()) {
                if (value == null) {
                    remove(USER_TYPE)
                }
                else {
                    putString(USER_TYPE, value.toString())
                }
                apply()
            }
        }

    var userId: Int?
        get() {
            return _sharedPreference.getInt(USER_ID, 0)
        }
        set(value) {
            with(_sharedPreference.edit()) {
                if (value == null) {
                    remove(USER_ID)
                }
                else {
                    putInt(USER_ID, value)
                }
                apply()
            }
        }

    companion object {
        const val PROJFAIR_TOKEN = "projfairToken"
        const val USER_TYPE = "userType"
        const val USER_ID = "userId"
    }
}

private fun String?.toUserStatusEnum(): UserStatus? {
    return try {
        this?.let {
            UserStatus.valueOf(it)
        } ?: UserStatus.UNKNOWN
    } catch (ex: Exception) {
        UserStatus.UNKNOWN
    }
}