package com.asad.data.model

import com.google.gson.annotations.SerializedName

data class ReviewResponseItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("author_details")
    val authorDetail: AuthorDetailResponse
)
