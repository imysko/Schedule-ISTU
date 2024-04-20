package me.progneo.campus.domain.usecase

import javax.inject.Inject
import me.progneo.campus.BuildConfig
import me.progneo.campus.domain.entities.TokenResponse
import me.progneo.campus.domain.repository.AuthRepository

interface GetTokenUseCase {

    suspend operator fun invoke(code: String): Result<TokenResponse>
}

class GetTokenUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository
) : GetTokenUseCase {

    override suspend operator fun invoke(code: String): Result<TokenResponse> {
        return authRepository.getToken(
            clientId = BuildConfig.CLIENT_ID,
            clientSecret = BuildConfig.CLIENT_SECRET,
            code = code
        )
    }
}
