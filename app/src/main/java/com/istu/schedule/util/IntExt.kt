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
