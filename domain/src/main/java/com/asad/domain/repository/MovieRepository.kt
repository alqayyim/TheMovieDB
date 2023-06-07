package com.asad.domain.repository

import androidx.paging.PagingData
import com.asad.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(path: String): Flow<PagingData<Movie>>
}