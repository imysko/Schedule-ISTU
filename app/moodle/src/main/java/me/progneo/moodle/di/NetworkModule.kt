package me.progneo.moodle.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Named
import me.progneo.moodle.util.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Named("MoodleBaseUrl")
    fun provideBaseUrl() = "https://projfair.istu.edu/" // TODO: set actual url

    @Provides
    @Named("MoodleRetrofit")
    fun provideMoodleRetrofit(
        @Named("MoodleBaseUrl") baseUrl: String,
        @Named("MoodleOkHttpClient") okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Named("MoodleOkHttpClient")
    fun provideMoodleOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
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
            .callTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }
}
