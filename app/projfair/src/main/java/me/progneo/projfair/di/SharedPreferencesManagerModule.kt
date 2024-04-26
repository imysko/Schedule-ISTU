package me.progneo.projfair.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.progneo.projfair.data.preference.ProjfairAccessTokenManager
import me.progneo.projfair.data.preference.ProjfairAccessTokenManagerImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SharedPreferencesManagerModule {

    @Binds
    internal abstract fun authStateManager(
        authStateManagerImpl: ProjfairAccessTokenManagerImpl
    ): ProjfairAccessTokenManager
}
