package me.progneo.campus.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import me.progneo.campus.domain.usecase.GetBlogPostListUseCase
import me.progneo.campus.domain.usecase.GetBlogPostListUseCaseImpl
import me.progneo.campus.domain.usecase.GetCountersUseCase
import me.progneo.campus.domain.usecase.GetCountersUseCaseImpl
import me.progneo.campus.domain.usecase.GetTokenUseCase
import me.progneo.campus.domain.usecase.GetTokenUseCaseImpl
import me.progneo.campus.domain.usecase.GetUserListUseCase
import me.progneo.campus.domain.usecase.GetUserListUseCaseImpl
import me.progneo.campus.domain.usecase.RefreshTokenUseCase
import me.progneo.campus.domain.usecase.RefreshTokenUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UseCaseModule {

    @Binds
    @Singleton
    internal abstract fun provideGetBlogPostUseCase(
        getBlogPostUseCaseImpl: GetBlogPostListUseCaseImpl
    ): GetBlogPostListUseCase

    @Binds
    @Singleton
    internal abstract fun provideGetCountersUseCase(
        getCountersUseCaseImpl: GetCountersUseCaseImpl
    ): GetCountersUseCase

    @Binds
    @Singleton
    internal abstract fun provideGetUserListUseCase(
        getUserListUseCaseImpl: GetUserListUseCaseImpl
    ): GetUserListUseCase

    @Binds
    @Singleton
    internal abstract fun provideGetTokenUseCase(
        getTokenUseCaseImpl: GetTokenUseCaseImpl
    ): GetTokenUseCase

    @Binds
    @Singleton
    internal abstract fun provideRefreshTokenUseCase(
        refreshTokenUseCaseImpl: RefreshTokenUseCaseImpl
    ): RefreshTokenUseCase
}
