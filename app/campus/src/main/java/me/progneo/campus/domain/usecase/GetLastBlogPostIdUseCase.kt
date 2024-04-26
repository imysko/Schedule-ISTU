package me.progneo.campus.domain.usecase

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import me.progneo.campus.domain.repository.BlogPostRepository

interface GetLastBlogPostIdUseCase {

    suspend operator fun invoke(): Flow<Int>
}

internal class GetLastBlogPostIdUseCaseImpl @Inject constructor(
    private val blogPostRepository: BlogPostRepository
) : GetLastBlogPostIdUseCase {

    override suspend operator fun invoke(): Flow<Int> {
        return blogPostRepository.getLastBlogPostId()
    }
}
