package com.istu.schedule.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.istu.schedule.data.preference.LocalTheme
import com.istu.schedule.data.preference.ThemePreference

private val lightColorScheme = lightColorScheme(
    primary = Blue,
    onPrimary = Color.White,
    primaryContainer = BlueContainer,
    secondary = Gray,
    background = Background,
    surface = Blue,
    onSurface = Color.White,
    tertiary = BlackText,
    error = Red,
    errorContainer = RedContainer,
)

@Composable
fun ScheduleISTUTheme(
    content: @Composable () -> Unit,
) {
    val theme = LocalTheme.current

    val colorScheme: ColorScheme =
        when (theme.value) {
            ThemePreference.UseDeviceTheme.value -> {
                if (isSystemInDarkTheme()) {
                    lightColorScheme
                } else {
                    lightColorScheme
                }
            }
            ThemePreference.Amoled.value -> lightColorScheme
            ThemePreference.Dark.value -> lightColorScheme
            else -> lightColorScheme
        }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(Color.Transparent)
        systemUiController.setSystemBarsColor(Color.Transparent)
        systemUiController.setNavigationBarColor(Color.Transparent)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
