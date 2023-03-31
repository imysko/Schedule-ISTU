package com.istu.schedule.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.istu.schedule.ui.fonts.montFamily

val Typography = Typography(
    displayLarge = TextStyle(fontFamily = montFamily, color = Color(0xFF383838)),
    displayMedium = TextStyle(fontFamily = montFamily, color = Color(0xFF383838)),
    displaySmall = TextStyle(fontFamily = montFamily, color = Color(0xFF383838)),
    headlineLarge = TextStyle(fontFamily = montFamily, color = Color(0xFF383838)),
    headlineMedium = TextStyle(fontFamily = montFamily, color = Color(0xFF383838)),
    headlineSmall = TextStyle(fontFamily = montFamily, color = Color(0xFF383838)),
    titleLarge = TextStyle(fontFamily = montFamily, color = Color(0xFF383838)),
    titleMedium = TextStyle(
        fontFamily = montFamily,
        color = Color(0xFF383838),
        fontSize = 16.0.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    titleSmall = TextStyle(fontFamily = montFamily, color = Color(0xFF383838)),
    bodyLarge = TextStyle(fontFamily = montFamily, color = Color(0xFF383838)),
    bodyMedium = TextStyle(
        fontFamily = montFamily,
        color = Color(0xFF383838),
        fontSize = 14.0.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = montFamily,
        color = Color(0xFF383838),
        fontSize = 12.0.sp,
    ),
    labelLarge = TextStyle(fontFamily = montFamily, color = Color(0xFF383838)),
    labelMedium = TextStyle(fontFamily = montFamily, color = Color(0xFF383838)),
    labelSmall = TextStyle(fontFamily = montFamily, color = Color(0xFF383838)),
)
