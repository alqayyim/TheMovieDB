package com.asad.domain.model

data class Review(
    val id: String,
    val author: String?,
    val content: String,
    val rating: Int,
    val username: String?,
    val urlAvatar: String?
)
