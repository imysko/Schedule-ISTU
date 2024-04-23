package me.progneo.campus.data.api.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import me.progneo.campus.data.api.service.AuthService
import me.progneo.campus.data.exception.RequestException
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
        )

        if (apiResponse.code() == HttpURLConnection.HTTP_OK) {
            apiResponse.body()?.let { tokenResponse ->
                return Result.success(tokenResponse)
            }
        }

        return Result.failure(
            RequestException(
                code = apiResponse.code(),
                message = apiResponse.message()
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
        )

        if (apiResponse.code() == HttpURLConnection.HTTP_OK) {
            apiResponse.body()?.let { tokenResponse ->
                return Result.success(tokenResponse)
            }
        }

        return Result.failure(
            RequestException(
                code = apiResponse.code(),
                message = apiResponse.message()
            )
        )
    }
}
