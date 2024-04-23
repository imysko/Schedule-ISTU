package me.progneo.campus.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Named
import me.progneo.campus.data.api.service.AuthService
import me.progneo.campus.data.preference.CampusAuthStateManager
import me.progneo.campus.data.preference.RefreshTokenManager
import me.progneo.campus.util.AuthInterceptor
import me.progneo.campus.util.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Named("CampusBaseUrl")
    fun provideCampusBaseUrl() = "https://int.istu.edu/rest/"

    @Provides
    @Named("BitrixAuthBaseUrl")
    fun provideBitrixAuthBaseUrl() = "https://oauth.bitrix.info/oauth/"

    @Provides
    @Named("CampusRetrofit")
    fun provideDataRetrofit(
        @Named("CampusBaseUrl") baseUrl: String,
        @Named("CampusOkHttpClient") okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Named("BitrixAuthRetrofit")
    fun provideAuthRetrofit(
        @Named("BitrixAuthBaseUrl") baseUrl: String,
        @Named("BitrixOkHttpClient") okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Named("CampusOkHttpClient")
    fun provideCampusOkHttpClient(
        @ApplicationContext context: Context,
        campusAuthStateManager: CampusAuthStateManager,
        refreshTokenManager: RefreshTokenManager,
        authService: AuthService
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(context))
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.HEADERS
                }
            )
            .addInterceptor(
                AuthInterceptor(
                    campusAuthStateManager = campusAuthStateManager,
                    refreshTokenManager = refreshTokenManager,
                    authService = authService
                )
            )
            .addInterceptor {
                val original = it.request()
                val newRequestBuilder = original.newBuilder()
                newRequestBuilder.addHeader("Content-Type", "application/json")
                newRequestBuilder.addHeader("Accept", "application/json")
                it.proceed(newRequestBuilder.build())
            }
            .callTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Named("BitrixOkHttpClient")
    fun provideBitrixOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(context))
            .build()
    }
}
