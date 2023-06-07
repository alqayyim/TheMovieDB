package com.asad.domain.usecase

import android.util.Log
import com.asad.core.network.FlowUseCase
import com.asad.domain.model.MovieDetailModel
import com.asad.domain.repository.MovieDetailRepository
import com.asad.core.data.Resource
import com.asad.domain.model.Trailer
import com.asad.domain.model.TrailerModel
import kotlinx.coroutines.flow.Flow

class TrailerUseCase(private val repo: MovieDetailRepository) : FlowUseCase<Int, TrailerModel>() {
    override suspend fun execute(parameters: Int?): Flow<Resource<TrailerModel>> {
        return repo.getTrailer(parameters!!)
    }
}