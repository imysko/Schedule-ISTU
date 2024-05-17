package me.progneo.moodle.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import me.progneo.moodle.data.api.service.NotificationsService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    internal fun provideNotificationsService(
        @Named("MoodleRetrofit") retrofit: Retrofit
    ): NotificationsService = retrofit.create(NotificationsService::class.java)
}
