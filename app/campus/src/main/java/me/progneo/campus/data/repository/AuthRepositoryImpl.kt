package me.progneo.campus.data.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import me.progneo.campus.data.exception.RequestException
import me.progneo.campus.data.service.AuthService
import me.progneo.campus.domain.entities.TokenResponse
import me.progneo.campus.domain.repository.AuthRepository

internal class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {

    override suspend fun getToken(
        clientId: String,
        clientSecret: String,
        code: String
    ): Result<TokenResponse> {
        val apiResponse = authService.getToken(
            clientId = clientId,
            clientSecret = clientSecret,
            code = code
        ).body()
        if (apiResponse != null) {
            return Result.success(apiResponse)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }

    override suspend fun refreshToken(
        clientId: String,
        clientSecret: String,
        refreshToken: String
    ): Result<TokenResponse> {
        val apiResponse = authService.refreshToken(
            clientId = clientId,
            clientSecret = clientSecret,
            refreshToken = refreshToken
        ).body()
        if (apiResponse != null) {
            return Result.success(apiResponse)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }
}
