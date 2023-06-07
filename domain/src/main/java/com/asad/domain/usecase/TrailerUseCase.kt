package com.asad.domain.usecase

import com.asad.core.data.Resource
import com.asad.core.network.FlowUseCase
import com.asad.domain.model.TrailerModel
import com.asad.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow

class TrailerUseCase(private val repo: MovieDetailRepository) : FlowUseCase<Int, TrailerModel>() {
    override suspend fun execute(parameters: Int?): Flow<Resource<TrailerModel>> {
        return repo.getTrailer(parameters!!)
    }
}