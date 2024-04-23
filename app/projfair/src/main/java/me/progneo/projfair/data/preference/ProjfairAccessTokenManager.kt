package me.progneo.projfair.data.preference

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

interface ProjfairAccessTokenManager {

    suspend fun save(newToken: String)

    suspend fun get(): String?

    suspend fun remove()
}

@Singleton
internal class ProjfairAccessTokenManagerImpl @Inject constructor(
    @Named("CampusSharedPreferences") private val sharedPreferences: SharedPreferences
) : ProjfairAccessTokenManager {

    override suspend fun save(newToken: String) {
        with(sharedPreferences.edit()) {
            putString(ACCESS_TOKEN_KEY, newToken)
            apply()
        }
    }

    override suspend fun get(): String? {
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
    }

    override suspend fun remove() {
        with(sharedPreferences.edit()) {
            remove(ACCESS_TOKEN_KEY)
            apply()
        }
    }

    companion object {
        private const val ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY"
    }
}
