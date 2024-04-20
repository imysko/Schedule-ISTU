package me.progneo.campus.data.preference

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

interface AuthStateManager {

    suspend fun save(newState: Boolean)

    suspend fun get(): Boolean

    suspend fun remove()
}

@Singleton
internal class AuthStateManagerImpl @Inject constructor(
    @Named("CampusSharedPreferences") private val sharedPreferences: SharedPreferences
) : AuthStateManager {

    override suspend fun save(newState: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(AUTH_STATE_KEY, newState)
            apply()
        }
    }

    override suspend fun get(): Boolean {
        return sharedPreferences.getBoolean(AUTH_STATE_KEY, false)
    }

    override suspend fun remove() {
        with(sharedPreferences.edit()) {
            remove(AUTH_STATE_KEY)
            apply()
        }
    }

    companion object {
        private const val AUTH_STATE_KEY = "AUTH_STATE_KEY"
    }
}
