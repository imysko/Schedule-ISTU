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
import me.progneo.campus.domain.usecase.GetUserByIdUseCase
import me.progneo.campus.domain.usecase.GetUserByIdUseCaseImpl
import me.progneo.campus.domain.usecase.GetUserListUseCase
import me.progneo.campus.domain.usecase.GetUserListUseCaseImpl
import me.progneo.campus.domain.usecase.RefreshTokenUseCase
import me.progneo.campus.domain.usecase.RefreshTokenUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UseCaseModule {

    @Binds
    @Singleton
    internal abstract fun bindGetBlogPostUseCase(
        getBlogPostUseCaseImpl: GetBlogPostListUseCaseImpl
    ): GetBlogPostListUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetCountersUseCase(
        getCountersUseCaseImpl: GetCountersUseCaseImpl
    ): GetCountersUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetUserListUseCase(
        getUserListUseCaseImpl: GetUserListUseCaseImpl
    ): GetUserListUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetUserByIdUseCase(
        getUserByIdUseCaseImpl: GetUserByIdUseCaseImpl
    ): GetUserByIdUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetTokenUseCase(
        getTokenUseCaseImpl: GetTokenUseCaseImpl
    ): GetTokenUseCase

    @Binds
    @Singleton
    internal abstract fun bindRefreshTokenUseCase(
        refreshTokenUseCaseImpl: RefreshTokenUseCaseImpl
    ): RefreshTokenUseCase
}
