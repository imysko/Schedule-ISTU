package com.istu.schedule.di

import com.istu.schedule.domain.usecase.schedule.GetClassroomsListUseCase
import com.istu.schedule.domain.usecase.schedule.GetClassroomsListUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.GetGroupsListUseCase
import com.istu.schedule.domain.usecase.schedule.GetGroupsListUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.GetInstitutesListUseCase
import com.istu.schedule.domain.usecase.schedule.GetInstitutesListUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.GetScheduleOnDayUseCase
import com.istu.schedule.domain.usecase.schedule.GetScheduleOnDayUseCaseImpl
import com.istu.schedule.domain.usecase.schedule.GetTeachersListUseCase
import com.istu.schedule.domain.usecase.schedule.GetTeachersListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    /** UseCases for "Projfair" */

    /** UseCases for schedule */
    @Binds
    @Singleton
    internal abstract fun bindGetScheduleOnDayUseCase(
        getScheduleOnDayUseCase: GetScheduleOnDayUseCaseImpl,
    ): GetScheduleOnDayUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetInstitutesListUseCase(
        getInstitutesListUseCase: GetInstitutesListUseCaseImpl,
    ): GetInstitutesListUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetGroupsListUseCase(
        getGroupsListUseCase: GetGroupsListUseCaseImpl,
    ): GetGroupsListUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetTeachersListUseCase(
        getTeachersListUseCase: GetTeachersListUseCaseImpl,
    ): GetTeachersListUseCase

    @Binds
    @Singleton
    internal abstract fun bindGetClassroomsListUseCase(
        getClassroomsListUseCase: GetClassroomsListUseCaseImpl,
    ): GetClassroomsListUseCase
}
