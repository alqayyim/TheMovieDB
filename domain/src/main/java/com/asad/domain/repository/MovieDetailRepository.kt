package com.asad.domain.repository

import com.asad.domain.model.MovieDetailModel
import com.asad.core.data.Resource
import com.asad.domain.model.TrailerModel
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {

    suspend fun getMovieDetail(movieId: Int?): Flow<Resource<MovieDetailModel>>

    suspend fun getTrailer(movieId: Int?): Flow<Resource<TrailerModel>>
}