package com.asad.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.asad.core.data.Resource
import com.asad.core.extension.catchError
import com.asad.core.extension.mapTo
import com.asad.data.service.MovieService
import com.asad.data.MoviesPagingSource
import com.asad.data.mapper.GenreMapper
import com.asad.data.mapper.MovieMapper
import com.asad.domain.model.Movie
import com.asad.domain.model.MovieGenre
import com.asad.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val service: MovieService,
    private val mapper: MovieMapper,
    private val genreMapper: GenreMapper
) : MovieRepository {

    override suspend fun getMoviesByGenre(genre: String): Flow<PagingData<Movie>> {
        val moviePager = Pager(
            config = PagingConfig(pageSize = 25, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(repository = service, genre) }
        ).flow

        return moviePager.map { pagingData ->
            pagingData.map {
                mapper.toMovie(it)
            }
        }
    }

    override suspend fun getGenres(): Flow<Resource<List<MovieGenre>>> {
        return flow {
            val response = service.getGenres()
            emit(response.mapTo(genreMapper))
        }.catchError()
    }
}