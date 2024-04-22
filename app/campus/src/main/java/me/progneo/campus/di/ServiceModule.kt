package me.progneo.campus.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import me.progneo.campus.data.service.AuthService
import me.progneo.campus.data.service.BlogPostService
import me.progneo.campus.data.service.CountersService
import me.progneo.campus.data.service.UserService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    internal fun provideBlogPostService(
        @Named("CampusRetrofit") retrofit: Retrofit
    ): BlogPostService = retrofit.create(BlogPostService::class.java)

    @Provides
    internal fun provideCountersService(
        @Named("CampusRetrofit") retrofit: Retrofit
    ): CountersService = retrofit.create(CountersService::class.java)

    @Provides
    internal fun provideUserService(
        @Named("CampusRetrofit") retrofit: Retrofit
    ): UserService = retrofit.create(UserService::class.java)

    @Provides
    internal fun provideAuthService(
        @Named("BitrixAuthRetrofit") retrofit: Retrofit
    ): AuthService = retrofit.create(AuthService::class.java)
}
