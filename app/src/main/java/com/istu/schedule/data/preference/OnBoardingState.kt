package com.istu.schedule.data.preference

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import com.istu.schedule.util.DataStoreKeys
import com.istu.schedule.util.NavDestinations
import com.istu.schedule.util.dataStore
import com.istu.schedule.util.put
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

sealed class OnBoardingState(val value: Boolean) : Preference() {

    object IsFirstLaunch : OnBoardingState(true)
    object IsNotFirstLaunch : OnBoardingState(false)

    override fun put(context: Context, scope: CoroutineScope) {
        scope.launch {
            context.dataStore.put(
                DataStoreKeys.IsFirstLaunch,
                value,
            )
        }
    }

    companion object {

        val default = IsFirstLaunch
        val values = listOf(IsFirstLaunch, IsNotFirstLaunch)

        val isFirstLaunch = IsFirstLaunch
        val isNotFirstLaunch = IsNotFirstLaunch

        fun fromPreferences(preferences: Preferences) =
            when (preferences[DataStoreKeys.IsFirstLaunch.key]) {
                true -> IsFirstLaunch
                false -> IsNotFirstLaunch
                else -> default
            }

        fun fromValue(value: Boolean): OnBoardingState =
            when (value) {
                true -> IsFirstLaunch
                false -> IsNotFirstLaunch
            }

        fun getStartDestination(value: Boolean): String =
            when(value) {
                true -> NavDestinations.ONBOARDING_PAGE
                false -> NavDestinations.MAIN_PAGE
            }
    }
}