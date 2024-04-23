package me.progneo.projfair.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import me.progneo.projfair.data.api.repository.CandidateRepositoryImpl
import me.progneo.projfair.data.api.repository.FiltersDataRepositoryImpl
import me.progneo.projfair.data.api.repository.ParticipationRepositoryImpl
import me.progneo.projfair.data.api.repository.ProjectRepositoryImpl
import me.progneo.projfair.data.api.repository.ProjectStateRepositoryImpl
import me.progneo.projfair.domain.repository.CandidateRepository
import me.progneo.projfair.domain.repository.FiltersDataRepository
import me.progneo.projfair.domain.repository.ParticipationRepository
import me.progneo.projfair.domain.repository.ProjectRepository
import me.progneo.projfair.domain.repository.ProjectStateRepository

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun bindProjectRepository(
        projectRepositoryImpl: ProjectRepositoryImpl
    ): ProjectRepository

    @Binds
    @Singleton
    internal abstract fun bindCandidateRepository(
        candidateRepositoryImpl: CandidateRepositoryImpl
    ): CandidateRepository

    @Binds
    @Singleton
    internal abstract fun bindProjectSateRepository(
        projectStateRepository: ProjectStateRepositoryImpl
    ): ProjectStateRepository

    @Binds
    @Singleton
    internal abstract fun bindParticipationRepository(
        participationRepository: ParticipationRepositoryImpl
    ): ParticipationRepository

    @Binds
    @Singleton
    internal abstract fun bindFiltersDataRepository(
        filtersDataRepository: FiltersDataRepositoryImpl
    ): FiltersDataRepository
}
