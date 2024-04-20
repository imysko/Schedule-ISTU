package me.progneo.campus.data.repository

import java.net.HttpURLConnection
import javax.inject.Inject
import me.progneo.campus.data.exception.RequestException
import me.progneo.campus.data.service.BlogPostService
import me.progneo.campus.domain.entities.BaseDataListResponse
import me.progneo.campus.domain.entities.BlogPost
import me.progneo.campus.domain.repository.BlogPostRepository

internal class BlogPostRepositoryImpl @Inject constructor(
    private val blogPostService: BlogPostService
) : BlogPostRepository {

    override suspend fun getBlogPostList(token: String): Result<BaseDataListResponse<BlogPost>> {
        val apiResponse = blogPostService.getBlogPostList(token).body()
        if (apiResponse != null) {
            return Result.success(apiResponse)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }
}
