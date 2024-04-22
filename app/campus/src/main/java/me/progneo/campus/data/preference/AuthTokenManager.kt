package me.progneo.campus.data.preference

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

interface AuthTokenManager {

    suspend fun save(newToken: String)

    suspend fun get(): String?

    suspend fun remove()
}

@Singleton
internal class AuthTokenManagerImpl @Inject constructor(
    @Named("CampusSharedPreferences") private val sharedPreferences: SharedPreferences
) : AuthTokenManager {

    override suspend fun save(newToken: String) {
        with(sharedPreferences.edit()) {
            putString(AUTH_TOKEN_KEY, newToken)
            apply()
        }
    }

    override suspend fun get(): String? {
        return sharedPreferences.getString(AUTH_TOKEN_KEY, null)
    }

    override suspend fun remove() {
        with(sharedPreferences.edit()) {
            remove(AUTH_TOKEN_KEY)
            apply()
        }
    }

    companion object {
        private const val AUTH_TOKEN_KEY = "AUTH_TOKEN_KEY"
    }
}
