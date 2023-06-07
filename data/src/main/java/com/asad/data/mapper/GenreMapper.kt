package com.asad.data.mapper

import com.asad.core.network.Mapper
import com.asad.data.model.GenreResponse
import com.asad.data.model.MovieResponse
import com.asad.data.model.MovieResponseItem
import com.asad.data.model.ReviewResponseItem
import com.asad.domain.model.*
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

class GenreMapper : Mapper<GenreResponse, List<MovieGenre>> {

    override fun to(t: GenreResponse): List<MovieGenre> {
        return with(t) {
            results.map {
                MovieGenre(
                    id = it.id,
                    name = it.name
                )
            }
        }
    }
}
