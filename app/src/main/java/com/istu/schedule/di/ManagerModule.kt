package com.istu.schedule.di

import com.istu.schedule.data.service.notification.NotificationManager
import com.istu.schedule.data.service.notification.NotificationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerModule {

    @Binds
    @Singleton
    internal abstract fun bindNotificationManager(
        notificationManagerImpl: NotificationManagerImpl
    ): NotificationManager
}
