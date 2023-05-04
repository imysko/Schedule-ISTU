package com.istu.schedule.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.istu.schedule.R

@Composable
fun Int.toProjectDifficulty(): String {
    return when (this) {
        1 -> stringResource(R.string.easy)
        2 -> stringResource(R.string.medium)
        3 -> stringResource(R.string.difficult)
        else -> stringResource(R.string.unknown)
    }
}

@Composable
fun Int.toParticipationPriorityText(): String {
    return when (this) {
        1 -> stringResource(R.string.high_priority)
        2 -> stringResource(R.string.medium_priority)
        3 -> stringResource(R.string.low_priority)
        4, 5 -> stringResource(R.string.automatic_allocation)
        else -> stringResource(R.string.unknown)
    }
}

@Composable
fun Int.totoParticipationRomanNumerals(): String {
    return when (this) {
        1 -> "I"
        2 -> "II"
        3 -> "III"
        4, 5 -> "A"
        else -> stringResource(R.string.unknown)
    }
}
