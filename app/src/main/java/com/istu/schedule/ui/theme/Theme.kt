package com.istu.schedule.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.istu.schedule.data.preference.LocalTheme
import com.istu.schedule.data.preference.ThemePreference

private val lightColorScheme = lightColorScheme(
    primary = Blue,
    primaryContainer = BlueContainer,
    secondary = Gray,
    background = Color.White,
    surface = Background,
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

    rememberSystemUiController().run {
        setStatusBarColor(Color.Transparent, !theme.isDarkTheme())
        setSystemBarsColor(Color.Transparent, !theme.isDarkTheme())
        setNavigationBarColor(Color.Transparent, !theme.isDarkTheme())
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
