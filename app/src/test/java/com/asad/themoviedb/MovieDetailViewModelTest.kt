package com.asad.themoviedb

import com.asad.core.data.Resource
import com.asad.domain.model.MovieDetailModel
import com.asad.domain.model.TrailerModel
import com.asad.domain.usecase.MovieDetailUseCase
import com.asad.domain.usecase.TrailerUseCase
import com.asad.themoviedb.presentation.detail.MovieDetailViewModel
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
class MovieDetailViewModelTest : BaseViewModelTest() {

    @RelaxedMockK
    private lateinit var movieDetailUseCase: MovieDetailUseCase

    @RelaxedMockK
    private lateinit var trailerMockUseCase: TrailerUseCase

    private lateinit var viewModel: MovieDetailViewModel

    @Before
    fun setup() {
        viewModel = MovieDetailViewModel(movieDetailUseCase, trailerMockUseCase)
    }

    @Test
    fun `Should return successful response when asked for movie detail`() = runTest {
        // Given
        val id = 999
        val movieDetailModel = MovieDetailModel(id)
        coEvery { movieDetailUseCase(id) } returns flowOf(Resource.Success(movieDetailModel))
        viewModel.getDetailMovie(id)

        // Verify
        viewModel.movieDetailResponse.observeOnce {
            Truth.assertThat(it).isNotNull()
            when (it) {
                is Resource.Success -> Truth.assertThat(it.model?.movieId).isEqualTo(999)
                else -> {}
            }
        }
    }

    @Test
    fun `Should return error when asked for movie detail`() = runTest {
        // Given
        val id = 999
        val error = Throwable("Mock error")
        coEvery { movieDetailUseCase(id) } returns flowOf(Resource.Error(error))
        viewModel.getDetailMovie(id)
        // Verify
        viewModel.movieDetailResponse.observeOnce {
            Truth.assertThat(it).isNotNull()
            when (it) {
                is Resource.Error -> Truth.assertThat(it.error.message).isEqualTo(error.message)
                else -> {}
            }
        }
    }

    @Test
    fun `Should return successful response when asked for trailer`() = runTest {
        // Given
        val id = 999
        val trailerModel = TrailerModel(id)
        coEvery { trailerMockUseCase(id) } returns flowOf(Resource.Success(trailerModel))
        viewModel.getTrailer(id)

        // Verify
        viewModel.trailerResponse.observeOnce {
            Truth.assertThat(it).isNotNull()
            when (it) {
                is Resource.Success -> Truth.assertThat(it.model?.id).isEqualTo(999)
                else -> {}
            }
        }
    }

    @Test
    fun `Should return error when asked for trailer`() = runTest {
        // Given
        val id = 999
        val error = Throwable("Mock error")
        coEvery { trailerMockUseCase(id) } returns flowOf(Resource.Error(error))
        viewModel.getTrailer(id)
        // Verify
        viewModel.trailerResponse.observeOnce {
            Truth.assertThat(it).isNotNull()
            when (it) {
                is Resource.Error -> Truth.assertThat(it.error.message).isEqualTo(error.message)
                else -> {}
            }
        }
    }
}
