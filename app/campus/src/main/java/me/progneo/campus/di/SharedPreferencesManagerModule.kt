package me.progneo.campus.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.progneo.campus.data.preference.CampusAuthStateManager
import me.progneo.campus.data.preference.CampusAuthStateManagerImpl
import me.progneo.campus.data.preference.LastSeenBlogPostIdManager
import me.progneo.campus.data.preference.LastSeenBlogPostIdManagerImpl
import me.progneo.campus.data.preference.RefreshTokenManager
import me.progneo.campus.data.preference.RefreshTokenManagerImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SharedPreferencesManagerModule {

    @Binds
    internal abstract fun authStateManager(
        authStateManagerImpl: CampusAuthStateManagerImpl
    ): CampusAuthStateManager

    @Binds
    internal abstract fun refreshTokenManager(
        refreshTokenManagerImpl: RefreshTokenManagerImpl
    ): RefreshTokenManager

    @Binds
    internal abstract fun lastBlogPostIdManager(
        lastBlogPostIdManagerImpl: LastSeenBlogPostIdManagerImpl
    ): LastSeenBlogPostIdManager
}
