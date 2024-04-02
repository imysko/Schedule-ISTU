package com.istu.schedule.di

import com.istu.schedule.data.repository.schedule.ClassroomsRepositoryImpl
import com.istu.schedule.data.repository.schedule.FavoriteClassroomsRepositoryImpl
import com.istu.schedule.data.repository.schedule.FavoriteGroupsRepositoryImpl
import com.istu.schedule.data.repository.schedule.FavoriteTeachersRepositoryImpl
import com.istu.schedule.data.repository.schedule.GroupsRepositoryImpl
import com.istu.schedule.data.repository.schedule.InstitutesRepositoryImpl
import com.istu.schedule.data.repository.schedule.ScheduleRepositoryImpl
import com.istu.schedule.data.repository.schedule.TeachersRepositoryImpl
import com.istu.schedule.domain.repository.schedule.ClassroomsRepository
import com.istu.schedule.domain.repository.schedule.FavoriteClassroomsRepository
import com.istu.schedule.domain.repository.schedule.FavoriteGroupsRepository
import com.istu.schedule.domain.repository.schedule.FavoriteTeachersRepository
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
        institutesRepository: InstitutesRepositoryImpl
    ): InstitutesRepository

    @Binds
    @Singleton
    abstract fun bindGroupsRepository(
        groupsRepository: GroupsRepositoryImpl
    ): GroupsRepository

    @Binds
    @Singleton
    abstract fun bindTeachersRepository(
        teachersRepository: TeachersRepositoryImpl
    ): TeachersRepository

    @Binds
    @Singleton
    abstract fun bindClassroomsRepository(
        classroomsRepository: ClassroomsRepositoryImpl
    ): ClassroomsRepository

    @Binds
    @Singleton
    abstract fun bindScheduleRepository(
        scheduleRepository: ScheduleRepositoryImpl
    ): ScheduleRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteGroupsRepository(
        favoriteGroupsRepository: FavoriteGroupsRepositoryImpl
    ): FavoriteGroupsRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteTeachersRepository(
        favoriteTeachersRepository: FavoriteTeachersRepositoryImpl
    ): FavoriteTeachersRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteClassroomsRepository(
        favoriteClassroomsRepository: FavoriteClassroomsRepositoryImpl
    ): FavoriteClassroomsRepository
}
