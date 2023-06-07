package com.asad.data.model

import com.google.gson.annotations.SerializedName

data class TrailerResponseItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("site")
    val site: String
)
