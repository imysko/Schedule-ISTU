package me.progneo.campus.domain.usecase

import javax.inject.Inject
import me.progneo.campus.BuildConfig
import me.progneo.campus.domain.entities.TokenResponse
import me.progneo.campus.domain.repository.AuthRepository

interface RefreshTokenUseCase {

    suspend operator fun invoke(refreshToken: String): Result<TokenResponse>
}

internal class RefreshTokenUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : RefreshTokenUseCase {

    override suspend operator fun invoke(refreshToken: String): Result<TokenResponse> {
        return authRepository.refreshToken(
            clientId = BuildConfig.CLIENT_ID,
            clientSecret = BuildConfig.CLIENT_SECRET,
            refreshToken = refreshToken
        )
    }
}
