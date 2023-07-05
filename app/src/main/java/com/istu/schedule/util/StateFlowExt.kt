package com.istu.schedule.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun <T> StateFlow<T>.collectAsStateValue(
    context: CoroutineContext = Dispatchers.Default
): T = collectAsState(context).value

@Composable
fun <T : R, R> Flow<T>.collectAsStateValue(
    initial: R,
    context: CoroutineContext = Dispatchers.Default
): R = collectAsState(initial, context).value
