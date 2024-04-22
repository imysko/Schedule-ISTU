package me.progneo.campus.data.service

import me.progneo.campus.domain.entities.BaseDataListResponse
import me.progneo.campus.domain.entities.BlogPost
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface BlogPostService {

    @GET("log.blogpost.get")
    suspend fun getBlogPostList(
        @Query("auth") token: String
    ): Response<BaseDataListResponse<BlogPost>>
}
