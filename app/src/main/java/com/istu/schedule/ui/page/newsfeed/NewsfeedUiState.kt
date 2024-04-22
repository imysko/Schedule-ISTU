package com.istu.schedule.ui.page.newsfeed

import me.progneo.campus.ui.entities.BlogPostUiModel

sealed class NewsfeedUiState {

    data object Loading : NewsfeedUiState()
    data object Error : NewsfeedUiState()
    data object NetworkUnavailable : NewsfeedUiState()
    data class Content(
        val blogPostList: List<BlogPostUiModel>
    ) : NewsfeedUiState()

    data object Unauthorized : NewsfeedUiState()
}
