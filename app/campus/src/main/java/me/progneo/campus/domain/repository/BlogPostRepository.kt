package me.progneo.campus.domain.repository

import kotlinx.coroutines.flow.Flow
import me.progneo.campus.domain.entities.BlogPost

interface BlogPostRepository {

    suspend fun getBlogPostList(): Result<List<BlogPost>>

    suspend fun getLastBlogPostId(): Flow<Int>
}
