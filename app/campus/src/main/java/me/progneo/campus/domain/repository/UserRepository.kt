package me.progneo.campus.domain.repository

import me.progneo.campus.domain.entities.User

interface UserRepository {

    suspend fun getUserList(
        userIdList: List<Int>,
        token: String
    ): Result<List<User>>

    suspend fun getUser(
        userId: Int,
        token: String
    ): Result<User>
}
