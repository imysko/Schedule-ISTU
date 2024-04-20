package me.progneo.campus.domain.entities

import com.google.gson.annotations.SerializedName
import java.util.Date

data class BlogPost(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("TITLE")
    val title: String,
    @SerializedName("DETAIL_TEXT")
    val detailText: String,
    @SerializedName("DATE_PUBLISH")
    val datePublished: Date,
    @SerializedName("AUTHOR_ID")
    val authorId: Int
)
