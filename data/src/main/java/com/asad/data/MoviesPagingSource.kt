package com.asad.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.asad.data.model.MovieResponseItem
import com.asad.data.service.MovieService

class MoviesPagingSource constructor(
    private val repository: MovieService,
    private val path: String
) : PagingSource<Int, MovieResponseItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponseItem> {
        val page = params.key ?: 1

        return try {
            repository.getMovies(path, page).run {
                LoadResult.Page(
                    data = this.results,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (page == this.total) null else page + 1
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
