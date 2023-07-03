package com.istu.schedule.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class AppColors(
    primary: Color,
    primaryContainer: Color,
    secondary: Color,
    textPrimary: Color,
    textSecondary: Color,
    background: Color,
    surface: Color,
    success: Color,
    warning: Color,
    error: Color,
    errorContainer: Color,
    isLight: Boolean
) {

    var primary by mutableStateOf(primary)
        private set
    var primaryContainer by mutableStateOf(primaryContainer)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var background by mutableStateOf(background)
        private set
    var surface by mutableStateOf(surface)
        private set
    var success by mutableStateOf(success)
        private set
    var warning by mutableStateOf(warning)
        private set
    var error by mutableStateOf(error)
        private set
    var errorContainer by mutableStateOf(errorContainer)
        private set
    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        primary: Color = this.primary,
        primaryContainer: Color = this.primaryContainer,
        secondary: Color = this.secondary,
        textPrimary: Color = this.textPrimary,
        textSecondary: Color = this.textSecondary,
        background: Color = this.background,
        surface: Color = this.surface,
        success: Color = this.success,
        warning: Color = this.warning,
        error: Color = this.error,
        errorContainer: Color = this.errorContainer,
        isLight: Boolean = this.isLight
    ): AppColors = AppColors(
        primary,
        primaryContainer,
        secondary,
        textPrimary,
        textSecondary,
        background,
        surface,
        success,
        warning,
        error,
        errorContainer,
        isLight
    )

    // will be explained later
    fun updateColorsFrom(other: AppColors) {
        primary = other.primary
        primaryContainer = other.primaryContainer
        secondary = other.secondary
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        background = other.background
        success = other.success
        warning = other.warning
        error = other.error
        errorContainer = other.errorContainer
    }
}

fun lightColors(
    primary: Color = Blue,
    primaryContainer: Color = BlueContainer,
    secondary: Color = Gray,
    textPrimary: Color = TextPrimary,
    textSecondary: Color = TextSecondary,
    background: Color = Background,
    surface: Color = Color.White,
    success: Color = Green,
    warning: Color = Orange,
    error: Color = Red,
    errorContainer: Color = RedContainer
): AppColors = AppColors(
    primary = primary,
    primaryContainer = primaryContainer,
    secondary = secondary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    surface = surface,
    success = success,
    warning = warning,
    error = error,
    errorContainer = errorContainer,
    isLight = true
)

// TODO: dark theme

internal val LocalColors = staticCompositionLocalOf { lightColors() }
