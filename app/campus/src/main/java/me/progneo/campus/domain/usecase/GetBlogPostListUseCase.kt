package me.progneo.campus.domain.usecase

import javax.inject.Inject
import me.progneo.campus.domain.entities.BlogPost
import me.progneo.campus.domain.repository.BlogPostRepository

interface GetBlogPostListUseCase {

    suspend operator fun invoke(): Result<List<BlogPost>>
}

internal class GetBlogPostListUseCaseImpl @Inject constructor(
    private val blogPostRepository: BlogPostRepository
) : GetBlogPostListUseCase {

    override suspend operator fun invoke(): Result<List<BlogPost>> {
        return blogPostRepository.getBlogPostList()
    }
}
