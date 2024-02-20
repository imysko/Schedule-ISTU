package com.istu.schedule.di

import com.istu.schedule.data.repository.schedule.ClassroomsRepositoryImpl
import com.istu.schedule.data.repository.schedule.GroupsRepositoryImpl
import com.istu.schedule.data.repository.schedule.InstitutesRepositoryImpl
import com.istu.schedule.data.repository.schedule.ScheduleRepositoryImpl
import com.istu.schedule.data.repository.schedule.TeachersRepositoryImpl
import com.istu.schedule.domain.repository.schedule.ClassroomsRepository
import com.istu.schedule.domain.repository.schedule.GroupsRepository
import com.istu.schedule.domain.repository.schedule.InstitutesRepository
import com.istu.schedule.domain.repository.schedule.ScheduleRepository
import com.istu.schedule.domain.repository.schedule.TeachersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindInstitutesRepository(
        institutesRepositoryImpl: InstitutesRepositoryImpl
    ): InstitutesRepository

    @Binds
    @Singleton
    abstract fun bindGroupsRepository(
        groupsRepositoryImpl: GroupsRepositoryImpl
    ): GroupsRepository

    @Binds
    @Singleton
    abstract fun bindTeachersRepository(
        teachersRepositoryImpl: TeachersRepositoryImpl
    ): TeachersRepository

    @Binds
    @Singleton
    abstract fun bindClassroomsRepository(
        classroomsRepositoryImpl: ClassroomsRepositoryImpl
    ): ClassroomsRepository

    @Binds
    @Singleton
    abstract fun bindScheduleRepository(
        scheduleRepositoryImpl: ScheduleRepositoryImpl
    ): ScheduleRepository
}
