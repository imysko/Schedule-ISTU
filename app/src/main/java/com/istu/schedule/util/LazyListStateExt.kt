package com.istu.schedule.util

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun LazyListState.isScrollingBackward(): Boolean {
    val offset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) { derivedStateOf { (firstVisibleItemScrollOffset - offset) > 0 } }.value
}

@Composable
fun LazyListState.isScrollingForward(): Boolean {
    val offset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) { derivedStateOf { (firstVisibleItemScrollOffset - offset) < 0 } }.value
}
