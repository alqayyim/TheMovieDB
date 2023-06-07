package com.asad.domain.repository

import androidx.paging.PagingData
import com.asad.core.data.Resource
import com.asad.domain.model.Movie
import com.asad.domain.model.MovieGenre
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMoviesByGenre(genre: String): Flow<PagingData<Movie>>

    suspend fun getGenres(): Flow<Resource<List<MovieGenre>>>

}