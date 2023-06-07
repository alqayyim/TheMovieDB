package com.asad.themoviedb.module

import com.asad.themoviedb.presentation.detail.MovieDetailViewModel
import com.asad.themoviedb.presentation.movies.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesViewModel(moviesUseCase = get()) }
    viewModel { MovieDetailViewModel(movieDetailUseCase = get(), trailerUseCase = get()) }
}