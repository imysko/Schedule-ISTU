package me.progneo.campus.data.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import me.progneo.campus.data.exception.RequestException
import me.progneo.campus.data.service.UserService
import me.progneo.campus.domain.entities.BaseDataListResponse
import me.progneo.campus.domain.entities.User
import me.progneo.campus.domain.repository.UserRepository

internal class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {

    override suspend fun getUserList(
        userIdList: List<Int>,
        token: String
    ): Result<BaseDataListResponse<User>> {
        val apiResponse = userService.getUserList(
            userIdList = userIdList,
            token = token
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
