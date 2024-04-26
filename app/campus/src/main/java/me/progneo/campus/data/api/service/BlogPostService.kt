package me.progneo.campus.data.api.service

import me.progneo.campus.domain.entities.BaseDataListResponse
import me.progneo.campus.domain.entities.BlogPost
import retrofit2.Response
import retrofit2.http.GET

internal interface BlogPostService {

    @GET("log.blogpost.get")
    suspend fun getBlogPostList(): Response<BaseDataListResponse<BlogPost>>
}
