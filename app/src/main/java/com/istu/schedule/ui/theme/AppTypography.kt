package com.istu.schedule.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.istu.schedule.ui.fonts.montFamily

data class AppTypography(
    val display: TextStyle = TextStyle(
        fontFamily = montFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 36.0.sp,
        lineHeight = 38.sp
    ),
    val pageTitle: TextStyle = TextStyle(
        fontFamily = montFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    val title: TextStyle = TextStyle(
        fontFamily = montFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 20.sp
    ),
    val subtitle: TextStyle = TextStyle(
        fontFamily = montFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp
    ),
    val bodyLarge: TextStyle = TextStyle(
        fontFamily = montFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    val bodyMedium: TextStyle = TextStyle(
        fontFamily = montFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp
    ),
    val bodySmall: TextStyle = TextStyle(
        fontFamily = montFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 15.sp
    ),
    val button: TextStyle = TextStyle(
        fontFamily = montFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    ),
    val caption: TextStyle = TextStyle(
        fontFamily = montFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    val labelMedium: TextStyle = TextStyle(
        fontFamily = montFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 11.0.sp
    ),
    val labelSmall: TextStyle = TextStyle(
        fontFamily = montFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 9.0.sp
    )
)

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }
