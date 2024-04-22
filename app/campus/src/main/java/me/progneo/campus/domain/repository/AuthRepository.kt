package me.progneo.campus.domain.repository

import me.progneo.campus.domain.entities.TokenResponse

interface AuthRepository {

    suspend fun getToken(
        clientId: String,
        clientSecret: String,
        code: String
    ): Result<TokenResponse>

    suspend fun refreshToken(
        clientId: String,
        clientSecret: String,
        refreshToken: String
    ): Result<TokenResponse>
}
