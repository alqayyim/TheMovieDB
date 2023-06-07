package com.asad.data.model

import com.google.gson.annotations.SerializedName

data class AuthorDetailResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("avatar_path")
    val avatar_path: String?
)
