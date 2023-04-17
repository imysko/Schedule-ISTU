package com.istu.schedule.ui.components.toolbar

import androidx.compose.runtime.Stable

@Stable
interface IToolbarState {
    val offset: Float
    val height: Float
    val progress: Float
    val consumed: Float
    var scrollTopLimitReached: Boolean
    var scrollOffset: Float
}
