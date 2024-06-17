package me.progneo.campus.data.preference

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

interface UserIdManager {

    suspend fun save(newId: Int)

    suspend fun get(): Int

    suspend fun remove()
}

@Singleton
internal class UserIdManagerImpl @Inject constructor(
    @Named("CampusSharedPreferences") private val sharedPreferences: SharedPreferences
) : UserIdManager {

    override suspend fun save(newId: Int) {
        with(sharedPreferences.edit()) {
            putInt(USER_ID_KEY, newId)
            apply()
        }
    }

    override suspend fun get(): Int {
        return sharedPreferences.getInt(USER_ID_KEY, -1)
    }

    override suspend fun remove() {
        with(sharedPreferences.edit()) {
            remove(USER_ID_KEY)
            apply()
        }
    }

    companion object {
        private const val USER_ID_KEY = "USER_ID_KEY"
    }
}
