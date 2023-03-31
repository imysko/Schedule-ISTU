package com.istu.schedule.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.istu.schedule.data.preference.LocalTheme
import com.istu.schedule.data.preference.ThemePreference

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
)

private val AmoledDarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    surface = Color.Black,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
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
                    DarkColorScheme
                } else {
                    LightColorScheme
                }
            }
            ThemePreference.Amoled.value -> AmoledDarkColorScheme
            ThemePreference.Dark.value -> DarkColorScheme
            else -> LightColorScheme
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
