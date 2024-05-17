package me.progneo.moodle.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import me.progneo.moodle.domain.usecase.GetNotificationsCountUseCase
import me.progneo.moodle.domain.usecase.GetNotificationsCountUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UseCaseModule {

    @Binds
    @Singleton
    internal abstract fun bindGetNotificationsCountUseCase(
        getNotificationsCountUseCaseImpl: GetNotificationsCountUseCaseImpl
    ): GetNotificationsCountUseCase
}
