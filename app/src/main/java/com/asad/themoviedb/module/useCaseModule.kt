package com.asad.themoviedb.module

import com.asad.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    single { MovieDetailUseCase(get()) }
    single { TrailerUseCase(get()) }
    single { ReviewUseCase(get()) }
    single { GenreUseCase(get()) }
    single { MovieByGenreUseCase(get()) }
}