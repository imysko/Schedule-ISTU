package me.progneo.campus.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import me.progneo.campus.data.repository.AuthRepositoryImpl
import me.progneo.campus.data.repository.BlogPostRepositoryImpl
import me.progneo.campus.data.repository.CountersRepositoryImpl
import me.progneo.campus.data.repository.UserRepositoryImpl
import me.progneo.campus.domain.repository.AuthRepository
import me.progneo.campus.domain.repository.BlogPostRepository
import me.progneo.campus.domain.repository.CountersRepository
import me.progneo.campus.domain.repository.UserRepository

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindBlogPostRepository(
        blogPostRepositoryImpl: BlogPostRepositoryImpl
    ): BlogPostRepository

    @Binds
    @Singleton
    internal abstract fun bindCountersRepository(
        countersRepositoryImpl: CountersRepositoryImpl
    ): CountersRepository

    @Binds
    @Singleton
    internal abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    internal abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}
