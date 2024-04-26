package me.progneo.campus.util

import android.content.SharedPreferences
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow

internal fun SharedPreferences.getIntFlowForKey(keyForInt: String) = callbackFlow {
    val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (keyForInt == key) {
            trySend(getInt(key, -1))
        }
    }
    registerOnSharedPreferenceChangeListener(listener)
    if (contains(keyForInt)) {
        send(getInt(keyForInt, -1))
    }
    awaitClose { unregisterOnSharedPreferenceChangeListener(listener) }
}.buffer(Channel.UNLIMITED)
