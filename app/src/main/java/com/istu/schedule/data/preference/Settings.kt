package com.istu.schedule.data.preference

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.istu.schedule.util.collectAsStateValue
import com.istu.schedule.util.dataStore
import kotlinx.coroutines.flow.map

data class Settings(
    // Languages
    val languages: LanguagesPreference = LanguagesPreference.default,
)

// Languages
val LocalLanguages =
    compositionLocalOf<LanguagesPreference> { LanguagesPreference.default }

@Composable
fun SettingsProvider(
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val settings = remember {
        context.dataStore.data.map {
            Log.i("SILog", "AppTheme: $it")
            it.toSettings()
        }
    }.collectAsStateValue(initial = Settings())

    CompositionLocalProvider(
        // Languages
        LocalLanguages provides settings.languages,
    ) {
        content()
    }
}
