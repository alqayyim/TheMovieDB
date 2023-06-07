package com.asad.domain.usecase

import androidx.paging.PagingData
import com.asad.core.network.PagingFlowUseCase
import com.asad.domain.model.Review
import com.asad.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow

class ReviewUseCase(private val repo: MovieDetailRepository) : PagingFlowUseCase<Int, PagingData<Review>>() {
    override suspend fun execute(parameters: Int?): Flow<PagingData<Review>> {
        return repo.getReviews(parameters!!)
    }
}