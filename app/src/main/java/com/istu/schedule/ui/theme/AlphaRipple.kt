package com.istu.schedule.ui.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
object AlphaRipple : RippleTheme {

    @Composable
    override fun defaultColor() = LocalContentColor.current

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.3f, 0.3f, 0.3f, 0.3f)
}
