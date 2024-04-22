package me.progneo.campus.data.service

import me.progneo.campus.domain.entities.BaseDataListResponse
import me.progneo.campus.domain.entities.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface UserService {

    @GET("user.get")
    suspend fun getUserList(
        @Query("auth") token: String,
        @Query("FILTER[ID][]") userIdList: List<Int>
    ): Response<BaseDataListResponse<User>>
}
