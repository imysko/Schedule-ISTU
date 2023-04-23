package com.istu.schedule.util

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

val Context.isFirstLaunch: Boolean
    get() = this.dataStore.get(DataStoreKeys.IsFirstLaunch) ?: true

val Context.languages: Int
    get() = this.dataStore.get(DataStoreKeys.Languages) ?: 0

suspend fun <T> DataStore<Preferences>.put(dataStoreKeys: DataStoreKeys<T>, value: T) {
    this.edit {
        withContext(Dispatchers.IO) {
            it[dataStoreKeys.key] = value
        }
    }
}

fun <T> DataStore<Preferences>.putBlocking(dataStoreKeys: DataStoreKeys<T>, value: T) {
    runBlocking {
        this@putBlocking.edit {
            it[dataStoreKeys.key] = value
        }
    }
}

fun <T> DataStore<Preferences>.get(dataStoreKeys: DataStoreKeys<T>): T? {
    return runBlocking {
        this@get.data.catch { exception ->
            if (exception is IOException) {
                Log.e("UMLog", "Get data store error $exception")
                exception.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map {
            it[dataStoreKeys.key]
        }.first()
    }
}

sealed class DataStoreKeys<T> {

    abstract val key: Preferences.Key<T>

    // Is first launch
    object IsFirstLaunch : DataStoreKeys<Boolean>() {

        override val key: Preferences.Key<Boolean>
            get() = booleanPreferencesKey("isFirstLaunch")
    }

    // Theme
    object DarkTheme : DataStoreKeys<Int>() {

        override val key: Preferences.Key<Int>
            get() = intPreferencesKey("darkTheme")
    }

    object AmoledDarkTheme : DataStoreKeys<Boolean>() {

        override val key: Preferences.Key<Boolean>
            get() = booleanPreferencesKey("amoledDarkTheme")
    }

    // Languages
    object Languages : DataStoreKeys<Int>() {

        override val key: Preferences.Key<Int>
            get() = intPreferencesKey("languages")
    }
}
