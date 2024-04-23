package me.progneo.campus.domain.usecase

import javax.inject.Inject
import me.progneo.campus.domain.entities.User
import me.progneo.campus.domain.repository.UserRepository

interface GetUserListUseCase {

    suspend operator fun invoke(
        userIdList: List<Int>
    ): Result<List<User>>
}

internal class GetUserListUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUserListUseCase {

    override suspend operator fun invoke(
        userIdList: List<Int>
    ): Result<List<User>> {
        return userRepository.getUserList(
            userIdList = userIdList
        )
    }
}
