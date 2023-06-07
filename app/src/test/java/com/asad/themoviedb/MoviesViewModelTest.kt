package com.asad.themoviedb

import com.asad.core.data.Resource
import com.asad.data.MoviesPagingSource
import com.asad.data.service.MovieService
import com.asad.domain.model.MovieGenre
import com.asad.domain.usecase.GenreUseCase
import com.asad.domain.usecase.MovieByGenreUseCase
import com.asad.themoviedb.presentation.movies.MoviesViewModel
import com.asad.themoviedb.utils.BaseViewModelTest
import com.asad.themoviedb.utils.observeOnce
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest : BaseViewModelTest() {

    @RelaxedMockK
    private lateinit var movieByGenreUseCase: MovieByGenreUseCase

    @RelaxedMockK
    private lateinit var genreUseCase: GenreUseCase

    @RelaxedMockK
    private lateinit var movieService: MovieService

    val genreId = "1, 2"
    private lateinit var viewModel: MoviesViewModel
    lateinit var moviesPagingSource: MoviesPagingSource

    @Before
    fun setup() {
        viewModel = MoviesViewModel(movieByGenreUseCase, genreUseCase)
        moviesPagingSource = MoviesPagingSource(movieService, genreId)
    }

    @Test
    fun `Should return successful response when asked for trailer`() = runTest {
        // Given
        val GENRE_1 = MovieGenre(1, "action")
        val GENRE_2 = MovieGenre(2, "animation")
        coEvery { genreUseCase() } returns flowOf(Resource.Success(listOf(GENRE_1, GENRE_2)))
        viewModel.getGenres()

        // Verify
        viewModel.genreResponse.observeOnce {
            Truth.assertThat(it).isNotNull()
            Truth.assertThat(it).isEqualTo(Resource.Success(listOf(GENRE_1, GENRE_2)))
            when (it) {
                is Resource.Success -> Truth.assertThat(it.model?.size).isEqualTo(2)
                else -> {}
            }
        }
    }

    @Test
    fun `Should return error when asked for trailer`() = runTest {
        // Given
        val error = Throwable("Mock error")
        coEvery { genreUseCase() } returns flowOf(Resource.Error(error))
        viewModel.getGenres()
        // Verify
        viewModel.genreResponse.observeOnce {
            Truth.assertThat(it).isNotNull()
            when (it) {
                is Resource.Error -> Truth.assertThat(it.error.message).isEqualTo(error.message)
                else -> {}
            }
        }
    }
}
