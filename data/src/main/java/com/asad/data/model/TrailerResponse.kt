package com.asad.data.model

import com.google.gson.annotations.SerializedName

data class TrailerResponse(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("results")
    var results: List<TrailerResponseItem>
)
