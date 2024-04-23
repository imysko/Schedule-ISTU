package me.progneo.campus.data.api.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import me.progneo.campus.data.api.service.BlogPostService
import me.progneo.campus.data.exception.RequestException
import me.progneo.campus.domain.entities.BlogPost
import me.progneo.campus.domain.repository.BlogPostRepository

internal class BlogPostRepositoryImpl @Inject constructor(
    private val blogPostService: BlogPostService
) : BlogPostRepository {

    private val _lastBlogPostId = MutableStateFlow(-1)

    override suspend fun getBlogPostList(): Result<List<BlogPost>> {
        val apiResponse = blogPostService.getBlogPostList()

        if (apiResponse.code() == HttpURLConnection.HTTP_OK) {
            apiResponse.body()?.let { listResponse ->
                val blogPostList = listResponse.result
                _lastBlogPostId.tryEmit(blogPostList.first().id)

                return Result.success(blogPostList)
            }
        }

        return Result.failure(
            RequestException(
                code = apiResponse.code(),
                message = apiResponse.message()
            )
        )
    }

    override suspend fun getLastBlogPostId(): Flow<Int> = _lastBlogPostId
}
