package com.istu.schedule.di

import com.istu.schedule.ui.util.VibrationManager
import com.istu.schedule.ui.util.VibrationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HelperModule {

    @Binds
    @Singleton
    abstract fun bindVibrationManager(
        vibrationManager: VibrationManagerImpl,
    ) : VibrationManager
}