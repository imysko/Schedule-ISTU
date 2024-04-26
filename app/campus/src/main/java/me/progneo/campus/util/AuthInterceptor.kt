package me.progneo.campus.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.progneo.campus.BuildConfig
import me.progneo.campus.data.api.service.AuthService
import me.progneo.campus.data.preference.CampusAuthStateManager
import me.progneo.campus.data.preference.RefreshTokenManager
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthInterceptor(
    private val campusAuthStateManager: CampusAuthStateManager,
    private val refreshTokenManager: RefreshTokenManager,
    private val authService: AuthService
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val refreshToken = runBlocking {
            refreshTokenManager.get()
        }

        if (refreshToken != null) {
            val response = runBlocking {
                authService.refreshToken(
                    clientId = BuildConfig.CLIENT_ID,
                    clientSecret = BuildConfig.CLIENT_SECRET,
                    refreshToken = refreshToken
                )
            }

            response.body()?.let { tokenResponse ->
                CoroutineScope(Dispatchers.IO).launch {
                    campusAuthStateManager.save(true)
                    refreshTokenManager.save(tokenResponse.refreshToken)
                }

                val newUrl = originalRequest.url.newBuilder()
                    .addQueryParameter("auth", tokenResponse.accessToken)
                    .build()
                val newRequest = originalRequest.newBuilder()
                    .url(newUrl)
                    .build()

                return chain.proceed(newRequest)
            } ?: run {
                CoroutineScope(Dispatchers.IO).launch {
                    campusAuthStateManager.remove()
                }
            }
        }

        return chain.proceed(originalRequest)
    }
}
