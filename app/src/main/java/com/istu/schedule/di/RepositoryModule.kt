package com.istu.schedule.di

import com.istu.schedule.data.repository.ProjectsRepositoryImpl
import com.istu.schedule.data.repository.schedule.InstitutesRepositoryImpl
import com.istu.schedule.domain.repository.ProjectsRepository
import com.istu.schedule.domain.repository.schedule.InstitutesRepository
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
    abstract fun bindProjectsRepository(projectsRepositoryImpl: ProjectsRepositoryImpl): ProjectsRepository

    @Binds
    @Singleton
    abstract fun bindScheduleRepository(scheduleRepositoryImpl: InstitutesRepositoryImpl): InstitutesRepository
}