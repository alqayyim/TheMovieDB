package com.asad.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.asad.data.service.MovieService
import com.asad.data.MoviesPagingSource
import com.asad.data.mapper.MovieMapper
import com.asad.domain.model.Movie
import com.asad.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val service: MovieService,
    private val mapper: MovieMapper
) : MovieRepository {

    override suspend fun getMovies(path: String): Flow<PagingData<Movie>> {
        val moviePager = Pager(
            config = PagingConfig(pageSize = 25, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(repository = service, path = path) }
        ).flow

        return moviePager.map { pagingData ->
            pagingData.map {
                mapper.toMovie(it)
            }
        }
    }
}