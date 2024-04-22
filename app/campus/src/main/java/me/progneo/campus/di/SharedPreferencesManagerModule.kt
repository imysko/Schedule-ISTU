package me.progneo.campus.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.progneo.campus.data.preference.AuthStateManager
import me.progneo.campus.data.preference.AuthStateManagerImpl
import me.progneo.campus.data.preference.AuthTokenManager
import me.progneo.campus.data.preference.AuthTokenManagerImpl
import me.progneo.campus.data.preference.RefreshTokenManager
import me.progneo.campus.data.preference.RefreshTokenManagerImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SharedPreferencesManagerModule {

    @Binds
    internal abstract fun authStateManager(
        authStateManagerImpl: AuthStateManagerImpl
    ): AuthStateManager

    @Binds
    internal abstract fun authTokenManager(
        authTokenManagerImpl: AuthTokenManagerImpl
    ): AuthTokenManager

    @Binds
    internal abstract fun refreshTokenManager(
        refreshTokenManagerImpl: RefreshTokenManagerImpl
    ): RefreshTokenManager
}
