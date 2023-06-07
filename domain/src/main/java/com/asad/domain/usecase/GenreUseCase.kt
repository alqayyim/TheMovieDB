package com.asad.domain.usecase

import com.asad.core.data.Resource
import com.asad.core.network.FlowUseCase
import com.asad.domain.model.MovieGenre
import com.asad.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GenreUseCase(private val repo: MovieRepository) : FlowUseCase<Any, List<MovieGenre>>() {

    override suspend fun execute(parameters: Any?): Flow<Resource<List<MovieGenre>>> {
        return repo.getGenres()
    }
}