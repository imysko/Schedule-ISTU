package com.istu.schedule.ui.components.toolbar

abstract class FixedScrollFlagState(heightRange: IntRange) : ScrollFlagState(heightRange) {

    final override val offset: Float = 0f
}
