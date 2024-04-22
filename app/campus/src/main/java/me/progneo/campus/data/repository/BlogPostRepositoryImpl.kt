package me.progneo.campus.data.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import me.progneo.campus.data.exception.RequestException
import me.progneo.campus.data.service.BlogPostService
import me.progneo.campus.domain.entities.BlogPost
import me.progneo.campus.domain.repository.BlogPostRepository

internal class BlogPostRepositoryImpl @Inject constructor(
    private val blogPostService: BlogPostService
) : BlogPostRepository {

    override suspend fun getBlogPostList(token: String): Result<List<BlogPost>> {
        val apiResponse = blogPostService.getBlogPostList(token)

        if (apiResponse.code() == HttpURLConnection.HTTP_OK) {
            apiResponse.body()?.let { listResponse ->
                return Result.success(listResponse.result)
            }
        }

        return Result.failure(
            RequestException(
                code = apiResponse.code(),
                message = apiResponse.message()
            )
        )
    }
}
