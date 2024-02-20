package com.istu.schedule.data.model

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.istu.schedule.data.enums.ProjfairAuthStatus
import com.istu.schedule.data.enums.Subgroup
import com.istu.schedule.data.enums.UserStatus
import com.istu.schedule.util.toSubgroupEnum
import com.istu.schedule.util.toUserStatusEnum
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.progneo.projfair.domain.model.FiltersState

@Singleton
class User @Inject constructor(private val _sharedPreference: SharedPreferences) {

    private val _projfairFiltersState = MutableStateFlow(FiltersState())
    val projfairFiltersState: StateFlow<FiltersState> = _projfairFiltersState.asStateFlow()

    private val _authStatus = MutableLiveData(ProjfairAuthStatus.UNDEFINED)
    val authStatus: LiveData<ProjfairAuthStatus> = _authStatus

    init {
        setAuth()
    }

    fun setProjfairFilters(projfairFiltersState: FiltersState) {
        _projfairFiltersState.value = projfairFiltersState
    }

    fun setFiltersChanged(isUpdated: Boolean) {
        _projfairFiltersState.value = _projfairFiltersState.value.copy(isChanged = isUpdated)
    }

    private fun setAuth() {
        if (projfairToken != null) {
            _authStatus.postValue(ProjfairAuthStatus.SUCCESS)
        } else {
            _authStatus.postValue(ProjfairAuthStatus.AUTH)
        }
    }

    fun loginProjfair(token: String) {
        projfairToken = token
    }

    fun logoutProjfair() {
        projfairToken = null
    }

    var projfairToken: String?
        get() {
            return _sharedPreference.getString(PROJFAIR_TOKEN, null)
        }
        set(value) {
            with(_sharedPreference.edit()) {
                if (value == null) {
                    remove(PROJFAIR_TOKEN)
                } else {
                    putString(PROJFAIR_TOKEN, value)
                }
                apply()
                setAuth()
            }
        }

    var userType: UserStatus
        get() {
            return _sharedPreference.getString(USER_TYPE, null).toUserStatusEnum()
                ?: UserStatus.UNKNOWN
        }
        set(value) {
            with(_sharedPreference.edit()) {
                putString(USER_TYPE, value.toString())
                apply()
            }
        }

    var userSubgroup: Subgroup
        get() {
            return _sharedPreference.getString(USER_SUBGROUP, null).toSubgroupEnum() ?: Subgroup.ALL
        }
        set(value) {
            with(_sharedPreference.edit()) {
                putString(USER_SUBGROUP, value.toString())
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
                } else {
                    putInt(USER_ID, value)
                }
                apply()
            }
        }

    var userDescription: String
        get() {
            return _sharedPreference.getString(USER_DESCRIPTION, null) ?: ""
        }
        set(value) {
            with(_sharedPreference.edit()) {
                putString(USER_DESCRIPTION, value)
                apply()
            }
        }

    companion object {
        const val PROJFAIR_TOKEN = "projfairToken"
        const val USER_TYPE = "userType"
        const val USER_SUBGROUP = "userSubgroup"
        const val USER_ID = "userId"
        const val USER_DESCRIPTION = "userDescription"
    }
}
