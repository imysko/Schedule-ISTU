package com.istu.schedule

import com.istu.schedule.data.repository.projfair.ProjectsRepositoryImpl
import com.istu.schedule.data.service.projfair.ProjectsService
import com.istu.schedule.domain.usecase.projfair.GetProjectUseCase
import com.istu.schedule.domain.usecase.projfair.GetProjectsListUseCase
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTest {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
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

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl("https://projfair.istu.edu/")
        .build()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getProjectTest() = runTest {
        val repository = ProjectsRepositoryImpl(retrofit.create(ProjectsService::class.java))
        val getProjectUseCase = GetProjectUseCase(repository)

        val response = getProjectUseCase.getProject(307)

        assert(response.getOrNull()?.id == 307)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getProjectsTest() = runTest {
        val repository = ProjectsRepositoryImpl(retrofit.create(ProjectsService::class.java))
        val getProjectsListUseCase = GetProjectsListUseCase(repository)

        val response = getProjectsListUseCase.getProjectsList(title = "Ярмарка проектов", page = 1)

        assert(response.getOrNull() != null)
        assert(
            response.getOrNull()?.all { project ->
                project.title.contains("Ярмарка проектов")
            }!!
        )
    }
}
