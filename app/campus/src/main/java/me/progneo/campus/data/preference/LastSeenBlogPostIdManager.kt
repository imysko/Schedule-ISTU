package me.progneo.campus.data.preference

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import me.progneo.campus.util.getIntFlowForKey

interface LastSeenBlogPostIdManager {

    suspend fun save(newId: Int)

    suspend fun get(): Int

    suspend fun getFlow(): Flow<Int>

    suspend fun remove()
}

@Singleton
internal class LastSeenBlogPostIdManagerImpl @Inject constructor(
    @Named("CampusSharedPreferences") private val sharedPreferences: SharedPreferences
) : LastSeenBlogPostIdManager {

    override suspend fun save(newId: Int) {
        with(sharedPreferences.edit()) {
            putInt(LAST_BLOG_POST_ID_KEY, newId)
            apply()
        }
    }

    override suspend fun get(): Int {
        return sharedPreferences.getInt(LAST_BLOG_POST_ID_KEY, -1)
    }

    override suspend fun getFlow(): Flow<Int> =
        sharedPreferences.getIntFlowForKey(LAST_BLOG_POST_ID_KEY)

    override suspend fun remove() {
        with(sharedPreferences.edit()) {
            remove(LAST_BLOG_POST_ID_KEY)
            apply()
        }
    }

    companion object {
        private const val LAST_BLOG_POST_ID_KEY = "LAST_BLOG_POST_ID_KEY"
    }
}
