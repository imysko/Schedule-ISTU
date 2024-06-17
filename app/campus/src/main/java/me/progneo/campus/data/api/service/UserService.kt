package me.progneo.campus.data.api.service

import me.progneo.campus.domain.entities.BaseDataListResponse
import me.progneo.campus.domain.entities.BaseDataResponse
import me.progneo.campus.domain.entities.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface UserService {

    @GET("user.get")
    suspend fun getUserList(
        @Query("FILTER[ID][]") userIdList: List<Int>
    ): Response<BaseDataListResponse<User>>

    @GET("user.current")
    suspend fun getCurrentUser(): Response<BaseDataResponse<User>>
}
