package com.asad.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.asad.data.model.ReviewResponseItem
import com.asad.data.service.MovieDetailService

class ReviewsPagingSource constructor(
    private val repository: MovieDetailService,
    private val movieId: Int
) : PagingSource<Int, ReviewResponseItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewResponseItem> {
        val page = params.key ?: 1

        return try {
            repository.getReviews(movieId, page).run {
                LoadResult.Page(
                    data = this.results,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (results.isEmpty()) null else page + 1
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ReviewResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
