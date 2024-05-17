package me.progneo.campus.domain.usecase

import javax.inject.Inject
import me.progneo.campus.domain.entities.User
import me.progneo.campus.domain.repository.UserRepository

interface GetCurrentUserUseCase {

    suspend operator fun invoke(): Result<User>
}

internal class GetCurrentUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetCurrentUserUseCase {

    override suspend operator fun invoke(): Result<User> {
        return userRepository.getCurrentUser()
    }
}
