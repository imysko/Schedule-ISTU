package com.istu.schedule.di

import com.istu.schedule.domain.usecase.schedule.GetScheduleOnDayUseCase
import com.istu.schedule.domain.usecase.schedule.GetScheduleOnDayUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    /** Repositories for "Projfair" */

    /** UseCases for schedule */
    @Binds
    @Singleton
    internal abstract fun bindGetScheduleOnDayUseCase(
        getScheduleOnDayUseCase: GetScheduleOnDayUseCaseImpl,
    ): GetScheduleOnDayUseCase
}