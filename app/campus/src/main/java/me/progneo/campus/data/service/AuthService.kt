package me.progneo.campus.data.service

import me.progneo.campus.domain.entities.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface AuthService {

    @GET("token/")
    suspend fun getToken(
        @Query("grant_type") grantType: String = "authorization_code",
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String
    ): Response<TokenResponse>

    @GET("token/")
    suspend fun refreshToken(
        @Query("grant_type") grantType: String = "refresh_token",
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("refresh_token") refreshToken: String
    ): Response<TokenResponse>
}
