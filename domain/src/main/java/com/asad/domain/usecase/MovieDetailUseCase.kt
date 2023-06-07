package com.asad.domain.usecase

import com.asad.core.network.FlowUseCase
import com.asad.domain.model.MovieDetailModel
import com.asad.domain.repository.MovieDetailRepository
import com.asad.core.data.Resource
import kotlinx.coroutines.flow.Flow

class MovieDetailUseCase(private val repo: MovieDetailRepository) : FlowUseCase<Int, MovieDetailModel>() {
    override suspend fun execute(parameters: Int?): Flow<Resource<MovieDetailModel>> {
        return repo.getMovieDetail(parameters!!)
    }
}