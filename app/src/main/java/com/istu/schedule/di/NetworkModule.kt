package com.istu.schedule.di

import android.content.Context
import android.content.SharedPreferences
import com.istu.schedule.data.service.schedule.ClassroomsService
import com.istu.schedule.data.service.schedule.GroupsService
import com.istu.schedule.data.service.schedule.InstitutesService
import com.istu.schedule.data.service.schedule.ScheduleService
import com.istu.schedule.data.service.schedule.TeachersService
import com.istu.schedule.util.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Named
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("ScheduleBaseUrl")
    fun provideScheduleBaseUrl() = "http://schedule-api.ovz2.j08801197.m397m.vps.myjino.ru/"

    @Provides
    @Named("ScheduleRetrofit")
    fun provideScheduleRetrofit(
        @Named("ScheduleBaseUrl") baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        prefs: SharedPreferences
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(context))
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.HEADERS
                }
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

    @Provides
    fun providerInstitutesService(
        @Named("ScheduleRetrofit") retrofit: Retrofit
    ): InstitutesService = retrofit.create(InstitutesService::class.java)

    @Provides
    fun providerGroupsService(
        @Named("ScheduleRetrofit") retrofit: Retrofit
    ): GroupsService = retrofit.create(GroupsService::class.java)

    @Provides
    fun providerTeachersService(
        @Named("ScheduleRetrofit") retrofit: Retrofit
    ): TeachersService = retrofit.create(TeachersService::class.java)

    @Provides
    fun providerClassroomsService(
        @Named("ScheduleRetrofit") retrofit: Retrofit
    ): ClassroomsService = retrofit.create(ClassroomsService::class.java)

    @Provides
    fun providerScheduleService(
        @Named("ScheduleRetrofit") retrofit: Retrofit
    ): ScheduleService = retrofit.create(ScheduleService::class.java)
}
