package com.istu.schedule.di

import android.content.SharedPreferences
import com.istu.schedule.data.service.projfair.CandidateService
import com.istu.schedule.data.service.projfair.ParticipationsService
import com.istu.schedule.data.service.projfair.ProjectStateService
import com.istu.schedule.data.service.projfair.ProjectsService
import com.istu.schedule.data.service.schedule.InstitutesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("ProjfairBaseUrl")
    fun provideProjfairBaseUrl() = "https://projfair.istu.edu/"

    @Provides
    @Named("ScheduleBaseUrl")
    fun provideScheduleBaseUrl() = "https://schedule-api.imysko.ru/"

    @Provides
    @Named("ProjfairRetrofit")
    fun provideProjfairRetrofit(
        @Named("ProjfairBaseUrl") baseUrl: String,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Named("ScheduleRetrofit")
    fun provideScheduleRetrofit(
        @Named("ScheduleBaseUrl") baseUrl: String,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    fun provideOkHttpClient(prefs: SharedPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                },
            )
            .addInterceptor {
                val original = it.request()
                val newRequestBuilder = original.newBuilder()
                newRequestBuilder.addHeader("Content-Type", "application/json")
                newRequestBuilder.addHeader("Accept", "application/json")
                it.proceed(newRequestBuilder.build())
            }
            .addInterceptor {
                val original = it.request()
                val newUrl = original.url
                    .newBuilder()
                    .build()
                val newRequest = original
                    .newBuilder()
                    .url(newUrl)
                    .build()

                it.proceed(newRequest)
            }
            .callTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    // Projfair
    @Provides
    fun providerProjectsService(
        @Named("ProjfairRetrofit") retrofit: Retrofit,
    ): ProjectsService = retrofit.create(ProjectsService::class.java)

    @Provides
    fun providerCandidateService(
        @Named("ProjfairRetrofit") retrofit: Retrofit,
    ): CandidateService = retrofit.create(CandidateService::class.java)

    @Provides
    fun providerProjectStatesService(
        @Named("ProjfairRetrofit") retrofit: Retrofit,
    ): ProjectStateService = retrofit.create(ProjectStateService::class.java)

    @Provides
    fun providerParticipationsService(
        @Named("ProjfairRetrofit") retrofit: Retrofit,
    ): ParticipationsService = retrofit.create(ParticipationsService::class.java)

    // Schedule
    @Provides
    fun providerInstitutesService(
        @Named("ScheduleRetrofit") retrofit: Retrofit,
    ): InstitutesService = retrofit.create(InstitutesService::class.java)
}
