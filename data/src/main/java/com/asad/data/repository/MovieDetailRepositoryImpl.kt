package com.asad.data.repository

import com.asad.core.extension.mapTo
import com.asad.data.mapper.MovieDetailMapper
import com.asad.data.service.MovieDetailService
import com.asad.domain.model.MovieDetailModel
import com.asad.domain.repository.MovieDetailRepository
import com.asad.core.data.Resource
import com.asad.core.extension.buildWrapper
import com.asad.data.mapper.TrailerMapper
import com.asad.domain.model.TrailerModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDetailRepositoryImpl(
    private val service: MovieDetailService,
    private val movieDetailMapper: MovieDetailMapper,
    private val trailerMapper: TrailerMapper
) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int?): Flow<Resource<MovieDetailModel>> {
        return flow {
            val response = service.getMovieDetail(movieId)
            emit(response.mapTo(movieDetailMapper))
        }.buildWrapper()
    }

    override suspend fun getTrailer(movieId: Int?): Flow<Resource<TrailerModel>> {
        return flow {
            val response = service.getTrailer(movieId)
            emit(response.mapTo(trailerMapper))
        }.buildWrapper()
    }
}