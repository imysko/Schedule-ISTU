package me.progneo.campus.domain.usecase

import javax.inject.Inject
import me.progneo.campus.domain.entities.User
import me.progneo.campus.domain.repository.UserRepository

interface GetUserByIdUseCase {

    suspend operator fun invoke(userId: Int): Result<User>
}

internal class GetUserByIdUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUserByIdUseCase {

    override suspend operator fun invoke(userId: Int): Result<User> {
        return userRepository.getUser(userId = userId)
    }
}
