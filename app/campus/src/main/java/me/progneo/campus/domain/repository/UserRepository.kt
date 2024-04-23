package me.progneo.campus.domain.repository

import me.progneo.campus.domain.entities.User

interface UserRepository {

    suspend fun getUserList(
        userIdList: List<Int>
    ): Result<List<User>>

    suspend fun getUser(
        userId: Int
    ): Result<User>
}
