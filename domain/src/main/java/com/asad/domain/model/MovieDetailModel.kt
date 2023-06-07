package com.asad.domain.model

import java.util.Date

data class MovieDetailModel(
    val movieId: Int,
    val popularity: Double? = null,
    val video: Boolean? = null,
    val adult: Boolean? = null,
    val poster: String? = null,
    val backdrop: String? = null,
    val genres: List<MovieGenre>? = null,
    val title: String? = null,
    val overview: String? = null,
    val imdbId: String? = null,
    val duration: Int? = null,
    val rating: String? = null,
    val voteCount: Int? = null,
    val releaseDate: Date? = null
)
