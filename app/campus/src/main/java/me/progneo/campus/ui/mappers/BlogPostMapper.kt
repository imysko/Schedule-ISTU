package me.progneo.campus.ui.mappers

import me.progneo.campus.domain.entities.BlogPost
import me.progneo.campus.ui.entities.BlogPostUiModel
import me.progneo.campus.util.removeBracketBlocks

fun BlogPost.toUiModel(): BlogPostUiModel {
    return BlogPostUiModel(
        id = this.id,
        title = this.title.removeBracketBlocks().trim(),
        detailText = this.detailText.removeBracketBlocks().trim(),
        datePublished = this.datePublished,
        authorId = this.authorId
    )
}
