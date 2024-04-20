package me.progneo.campus.domain.repository

import me.progneo.campus.domain.entities.BaseDataListResponse
import me.progneo.campus.domain.entities.BlogPost

interface BlogPostRepository {

    suspend fun getBlogPostList(token: String): Result<BaseDataListResponse<BlogPost>>
}
