package me.progneo.campus.domain.repository

import me.progneo.campus.domain.entities.BlogPost

interface BlogPostRepository {

    suspend fun getBlogPostList(token: String): Result<List<BlogPost>>
}
