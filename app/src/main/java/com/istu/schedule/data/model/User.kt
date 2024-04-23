package com.istu.schedule.data.model

import android.content.SharedPreferences
import com.istu.schedule.domain.entities.Subgroup
import com.istu.schedule.domain.entities.UserStatus
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

    fun setProjfairFilters(projfairFiltersState: FiltersState) {
        _projfairFiltersState.value = projfairFiltersState
    }

    fun setFiltersChanged(isUpdated: Boolean) {
        _projfairFiltersState.value = _projfairFiltersState.value.copy(isChanged = isUpdated)
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
        const val USER_TYPE = "userType"
        const val USER_SUBGROUP = "userSubgroup"
        const val USER_ID = "userId"
        const val USER_DESCRIPTION = "userDescription"
    }
}
