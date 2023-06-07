package com.asad.data.mapper

import com.asad.core.extension.getPosterUrl
import com.asad.core.network.Mapper
import com.asad.data.model.MovieResponse
import com.asad.data.model.MovieResponseItem
import com.asad.data.model.ReviewResponseItem
import com.asad.domain.model.Movie
import com.asad.domain.model.MovieModel
import com.asad.domain.model.Review
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
                        it.posterPath?.getPosterUrl(),
                        it.adult,
                        it.backdropPath?.getPosterUrl(),
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
                posterPath?.getPosterUrl(),
                adult,
                backdropPath?.getPosterUrl(),
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
