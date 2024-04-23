package me.progneo.campus.data.api.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import me.progneo.campus.data.api.service.UserService
import me.progneo.campus.data.exception.RequestException
import me.progneo.campus.domain.entities.User
import me.progneo.campus.domain.repository.UserRepository

internal class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {

    private val cachedList: MutableList<User> = mutableListOf()

    override suspend fun getUserList(
        userIdList: List<Int>

    ): Result<List<User>> {
        val apiResponse = userService.getUserList(userIdList = userIdList)

        if (apiResponse.code() == HttpURLConnection.HTTP_OK) {
            apiResponse.body()?.let { listResponse ->
                val userList = listResponse.result
                cachedList.let { list ->
                    list.addAll(userList)
                    list.distinctBy { it.id }
                }

                return Result.success(userList)
            }
        }

        return Result.failure(
            RequestException(
                code = apiResponse.code(),
                message = apiResponse.message()
            )
        )
    }

    override suspend fun getUser(userId: Int): Result<User> {
        return cachedList.find { it.id == userId }?.let { user ->
            Result.success(user)
        } ?: run {
            val apiResponse = userService.getUserList(userIdList = listOf(userId))

            if (apiResponse.code() == HttpURLConnection.HTTP_OK) {
                apiResponse.body()?.let { response ->
                    if (response.result.isNotEmpty()) {
                        return Result.success(response.result.first())
                    }
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
}
