package me.progneo.campus.ui.entities

import java.util.Date
import me.progneo.campus.domain.entities.User

data class BlogPostUiModel(
    val id: Int,
    val title: String,
    val detailText: String,
    val datePublished: Date,
    val authorId: Int,
    var author: User? = null
)
