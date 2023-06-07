package com.asad.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.asad.core.data.Resource
import com.asad.core.extension.catchError
import com.asad.core.extension.mapTo
import com.asad.core.extension.onProgress
import com.asad.data.ReviewsPagingSource
import com.asad.data.mapper.MovieDetailMapper
import com.asad.data.mapper.ReviewMapper
import com.asad.data.mapper.TrailerMapper
import com.asad.data.service.MovieDetailService
import com.asad.domain.model.MovieDetailModel
import com.asad.domain.model.Review
import com.asad.domain.model.TrailerModel
import com.asad.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MovieDetailRepositoryImpl(
    private val service: MovieDetailService,
    private val movieDetailMapper: MovieDetailMapper,
    private val trailerMapper: TrailerMapper,
    private val reviewMapper: ReviewMapper
) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int?): Flow<Resource<MovieDetailModel>> {
        return flow {
            val response = service.getMovieDetail(movieId)
            emit(response.mapTo(movieDetailMapper))
        }.onProgress().catchError()
    }

    override suspend fun getTrailer(movieId: Int?): Flow<Resource<TrailerModel>> {
        return flow {
            val response = service.getTrailer(movieId)
            emit(response.mapTo(trailerMapper))
        }.onProgress().catchError()
    }

    override suspend fun getReviews(movieId: Int?): Flow<PagingData<Review>> {
        val moviePager = Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { ReviewsPagingSource(repository = service, movieId = movieId ?: 0) }
        ).flow

        return moviePager.map { pagingData ->
            pagingData.map {
                reviewMapper.toReview(it)
            }
        }
    }
}