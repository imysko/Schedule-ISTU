package com.istu.schedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.istu.schedule.data.preference.LanguagesPreference
import com.istu.schedule.data.preference.SettingsProvider
import com.istu.schedule.ui.components.navigation.NavGraph
import com.istu.schedule.ui.theme.ScheduleISTUTheme
import com.istu.schedule.util.languages
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        LanguagesPreference.fromValue(languages).let {
            if (it == LanguagesPreference.UseDeviceLanguages) return@let
            it.setLocale(this)
        }

        setContent {
            SettingsProvider {
                ScheduleISTUTheme {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}