package com.asad.data.mapper

import com.asad.core.network.Mapper
import com.asad.data.model.MovieResponse
import com.asad.data.model.MovieResponseItem
import com.asad.domain.model.Movie
import com.asad.domain.model.MovieModel
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

class MovieMapper : Mapper<MovieResponse, MovieModel> {

    override fun to(t: MovieResponse): MovieModel {
        return with(t) {
            MovieModel(
                total = total,
                page = page,
                movies = results.map {
                    Movie(
                        it.id,
                        it.popularity,
                        it.video,
                        it.posterPath?.let { "https://image.tmdb.org/t/p/original$it" },
                        it.adult,
                        it.backdropPath?.let { "https://image.tmdb.org/t/p/original$it" },
                        it.originalLanguage,
                        it.originalTitle,
                        it.title,
                        it.voteAverage,
                        it.overview,
                        it.releaseDate?.let { date ->
                            if (date.isNotEmpty()) {
                                val parsedDate =
                                    SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(
                                        date
                                    ) ?: Date()
                                SimpleDateFormat("yyyy", Locale.getDefault()).format(parsedDate)
                            } else {
                                null
                            }
                        }
                    )
                }
            )
        }
    }

    fun toMovie(t: MovieResponseItem): Movie {
        return with(t) {
            Movie(
                id,
                popularity,
                video,
                posterPath?.let { "https://image.tmdb.org/t/p/original$it" },
                adult,
                backdropPath?.let { "https://image.tmdb.org/t/p/original$it" },
                originalLanguage,
                originalTitle,
                title,
                voteAverage,
                overview,
                releaseDate?.let { date ->
                    if (date.isNotEmpty()) {
                        val parsedDate =
                            SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(
                                date
                            ) ?: Date()
                        SimpleDateFormat("yyyy", Locale.getDefault()).format(parsedDate)
                    } else {
                        null
                    }
                }
            )
        }
    }
}
