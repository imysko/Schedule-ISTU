package me.progneo.projfair.util

import kotlinx.coroutines.runBlocking
import me.progneo.projfair.data.preference.ProjfairAccessTokenManager
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthInterceptor(
    private val projfairAccessTokenManager: ProjfairAccessTokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val accessToken = runBlocking { projfairAccessTokenManager.get() }
        if (accessToken != null) {
            val newRequest = originalRequest.newBuilder()
                .addHeader("Cookie", accessToken)
                .build()

            return chain.proceed(newRequest)
        }

        return chain.proceed(originalRequest)
    }
}
