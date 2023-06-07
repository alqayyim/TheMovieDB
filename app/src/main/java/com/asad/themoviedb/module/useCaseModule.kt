package com.asad.themoviedb.module

import com.asad.domain.usecase.MovieDetailUseCase
import com.asad.domain.usecase.MovieUseCase
import com.asad.domain.usecase.ReviewUseCase
import com.asad.domain.usecase.TrailerUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MovieUseCase(get()) }
    single { MovieDetailUseCase(get()) }
    single { TrailerUseCase(get()) }
    single { ReviewUseCase(get()) }
}