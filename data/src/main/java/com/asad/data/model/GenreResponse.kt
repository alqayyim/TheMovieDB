package com.asad.data.model

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    var results: List<GenreResponseItem>
)
