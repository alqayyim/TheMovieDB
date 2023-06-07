package com.asad.domain.usecase

import androidx.paging.PagingData
import com.asad.core.network.PagingFlowUseCase
import com.asad.domain.model.Movie
import com.asad.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieByGenreUseCase(private val repo: MovieRepository) : PagingFlowUseCase<String, PagingData<Movie>>() {
    override suspend fun execute(parameters: String?): Flow<PagingData<Movie>> {
        return repo.getMoviesByGenre(parameters!!)
    }
}