package com.asad.themoviedb.module

import com.asad.themoviedb.presentation.detail.MovieDetailViewModel
import com.asad.themoviedb.presentation.movies.MoviesViewModel
import com.asad.themoviedb.presentation.reviews.ReviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesViewModel(genreUseCase = get(), moviesByGenreUseCase = get()) }
    viewModel { MovieDetailViewModel(movieDetailUseCase = get(), trailerUseCase = get()) }
    viewModel { ReviewViewModel(reviewUseCase = get()) }
}
