package me.progneo.campus.data.preference

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

interface RefreshTokenManager {

    suspend fun save(newToken: String)

    suspend fun get(): String?

    suspend fun remove()
}

@Singleton
internal class RefreshTokenManagerImpl @Inject constructor(
    @Named("CampusSharedPreferences") private val sharedPreferences: SharedPreferences
) : RefreshTokenManager {

    override suspend fun save(newToken: String) {
        with(sharedPreferences.edit()) {
            putString(REFRESH_TOKEN_KEY, newToken)
            apply()
        }
    }

    override suspend fun get(): String? {
        return sharedPreferences.getString(REFRESH_TOKEN_KEY, null)
    }

    override suspend fun remove() {
        with(sharedPreferences.edit()) {
            remove(REFRESH_TOKEN_KEY)
            apply()
        }
    }

    companion object {
        private const val REFRESH_TOKEN_KEY = "REFRESH_TOKEN_KEY"
    }
}
