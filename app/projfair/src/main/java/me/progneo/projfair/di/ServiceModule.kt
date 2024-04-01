package me.progneo.projfair.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import me.progneo.projfair.data.service.CandidateService
import me.progneo.projfair.data.service.FiltersDataService
import me.progneo.projfair.data.service.ParticipationService
import me.progneo.projfair.data.service.ProjectStateService
import me.progneo.projfair.data.service.ProjectsService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    internal fun providerProjectsService(
        @Named("ProjfairRetrofit") retrofit: Retrofit
    ): ProjectsService = retrofit.create(ProjectsService::class.java)

    @Provides
    internal fun providerCandidateService(
        @Named("ProjfairRetrofit") retrofit: Retrofit
    ): CandidateService = retrofit.create(CandidateService::class.java)

    @Provides
    internal fun providerProjectStatesService(
        @Named("ProjfairRetrofit") retrofit: Retrofit
    ): ProjectStateService = retrofit.create(ProjectStateService::class.java)

    @Provides
    internal fun providerParticipationService(
        @Named("ProjfairRetrofit") retrofit: Retrofit
    ): ParticipationService = retrofit.create(ParticipationService::class.java)

    @Provides
    internal fun providerFiltersDataService(
        @Named("ProjfairRetrofit") retrofit: Retrofit
    ): FiltersDataService = retrofit.create(FiltersDataService::class.java)
}
