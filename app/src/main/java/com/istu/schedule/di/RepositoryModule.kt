package com.istu.schedule.di

import com.istu.schedule.data.repository.projfair.CandidateRepositoryImpl
import com.istu.schedule.data.repository.projfair.FiltersDataRepositoryImpl
import com.istu.schedule.data.repository.projfair.ParticipationsRepositoryImpl
import com.istu.schedule.data.repository.projfair.ProjectStateRepositoryImpl
import com.istu.schedule.data.repository.projfair.ProjectsRepositoryImpl
import com.istu.schedule.data.repository.schedule.ClassroomsRepositoryImpl
import com.istu.schedule.data.repository.schedule.FavoriteClassroomsRepositoryImpl
import com.istu.schedule.data.repository.schedule.FavoriteGroupsRepositoryImpl
import com.istu.schedule.data.repository.schedule.FavoriteTeachersRepositoryImpl
import com.istu.schedule.data.repository.schedule.GroupsRepositoryImpl
import com.istu.schedule.data.repository.schedule.InstitutesRepositoryImpl
import com.istu.schedule.data.repository.schedule.ScheduleRepositoryImpl
import com.istu.schedule.data.repository.schedule.TeachersRepositoryImpl
import com.istu.schedule.domain.repository.projfair.CandidateRepository
import com.istu.schedule.domain.repository.projfair.FiltersDataRepository
import com.istu.schedule.domain.repository.projfair.ParticipationsRepository
import com.istu.schedule.domain.repository.projfair.ProjectStateRepository
import com.istu.schedule.domain.repository.projfair.ProjectsRepository
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

    /** Repositories for "Projfair" */
    @Binds
    @Singleton
    abstract fun bindProjectsRepository(
        projectsRepository: ProjectsRepositoryImpl,
    ): ProjectsRepository

    @Binds
    @Singleton
    abstract fun bindCandidateRepository(
        candidateRepository: CandidateRepositoryImpl,
    ): CandidateRepository

    @Binds
    @Singleton
    abstract fun bindProjectSateRepository(
        projectStateRepository: ProjectStateRepositoryImpl,
    ): ProjectStateRepository

    @Binds
    @Singleton
    abstract fun bindParticipationsRepository(
        participationsRepository: ParticipationsRepositoryImpl,
    ): ParticipationsRepository

    @Binds
    @Singleton
    abstract fun bindFiltersDataRepository(
        filtersDataRepository: FiltersDataRepositoryImpl,
    ): FiltersDataRepository

    /** Repositories for schedule */
    @Binds
    @Singleton
    abstract fun bindInstitutesRepository(
        institutesRepository: InstitutesRepositoryImpl,
    ): InstitutesRepository

    @Binds
    @Singleton
    abstract fun bindGroupsRepository(
        groupsRepository: GroupsRepositoryImpl,
    ): GroupsRepository

    @Binds
    @Singleton
    abstract fun bindTeachersRepository(
        teachersRepository: TeachersRepositoryImpl,
    ): TeachersRepository

    @Binds
    @Singleton
    abstract fun bindClassroomsRepository(
        classroomsRepository: ClassroomsRepositoryImpl,
    ): ClassroomsRepository

    @Binds
    @Singleton
    abstract fun bindScheduleRepository(
        scheduleRepository: ScheduleRepositoryImpl,
    ): ScheduleRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteGroupsRepository(
        favoriteGroupsRepository: FavoriteGroupsRepositoryImpl,
    ): FavoriteGroupsRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteTeachersRepository(
        favoriteTeachersRepository: FavoriteTeachersRepositoryImpl,
    ): FavoriteTeachersRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteClassroomsRepository(
        favoriteClassroomsRepository: FavoriteClassroomsRepositoryImpl,
    ): FavoriteClassroomsRepository
}
