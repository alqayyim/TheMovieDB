package com.asad.domain.model

data class ReviewModel(
    val total: Int = 0,
    val page: Int = 0,
    val results: List<Review>? = null
)
