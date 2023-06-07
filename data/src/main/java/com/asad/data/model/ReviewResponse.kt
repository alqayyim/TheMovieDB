package com.asad.data.model

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    @SerializedName("total_pages", alternate = ["total_results"])
    val total: Int = 0,
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    var results: List<ReviewResponseItem>
)
