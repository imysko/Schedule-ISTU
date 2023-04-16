package com.istu.schedule.data.preference

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.datastore.preferences.core.Preferences
import com.istu.schedule.R
import com.istu.schedule.util.DataStoreKeys
import com.istu.schedule.util.dataStore
import com.istu.schedule.util.put
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

sealed class ThemePreference(val value: Int) : Preference() {
    object UseDeviceTheme : ThemePreference(0)
    object Light : ThemePreference(1)
    object Dark : ThemePreference(2)
    object Amoled : ThemePreference(3)

    override fun put(context: Context, scope: CoroutineScope) {
        scope.launch {
            context.dataStore.put(
                DataStoreKeys.DarkTheme,
                value,
            )
        }
    }

    fun toDesc(context: Context): String =
        when (this) {
            UseDeviceTheme -> context.getString(R.string.use_device_theme)
            Light -> context.getString(R.string.light)
            Dark -> context.getString(R.string.dark)
            Amoled -> context.getString(R.string.amoled)
        }

    @Composable
    @ReadOnlyComposable
    fun isDarkTheme(): Boolean = when (this) {
        UseDeviceTheme -> isSystemInDarkTheme()
        Light -> false
        Dark -> true
        Amoled -> true
    }

    companion object {

        val default = UseDeviceTheme
        val values = listOf(UseDeviceTheme, Light, Dark, Amoled)

        fun fromPreferences(preferences: Preferences) =
            when (preferences[DataStoreKeys.DarkTheme.key]) {
                0 -> UseDeviceTheme
                1 -> Light
                2 -> Dark
                3 -> Amoled
                else -> default
            }
    }
}
