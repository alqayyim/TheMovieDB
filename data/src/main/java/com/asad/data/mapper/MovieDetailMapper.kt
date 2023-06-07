package com.asad.data.mapper

import com.asad.core.extension.getPosterUrl
import com.asad.core.network.Mapper
import com.asad.data.model.MovieDetailResponse
import com.asad.domain.model.MovieDetailModel
import java.text.SimpleDateFormat
import java.util.Locale

class MovieDetailMapper :
    Mapper<MovieDetailResponse, MovieDetailModel> {

    override fun to(t: MovieDetailResponse): MovieDetailModel {
        return with(t) {
            MovieDetailModel(
                id,
                popularity,
                video,
                adult,
                posterPath?.getPosterUrl(),
                backdropPath?.getPosterUrl(),
                genres,
                title,
                overview,
                imdbId,
                runtime,
                voteAverage?.let {
                    String.format("%.1f", voteAverage)
                },
                voteCount,
                releaseDate?.let { date ->
                    if (date.isNotEmpty()) {
                        SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(
                            date
                        )
                    } else {
                        null
                    }
                }
            )
        }
    }
}
