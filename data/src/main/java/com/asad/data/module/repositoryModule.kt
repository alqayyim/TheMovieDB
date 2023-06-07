package com.asad.data.module

import com.asad.data.repository.MovieDetailRepositoryImpl
import com.asad.data.repository.MovieRepositoryImpl
import com.asad.domain.repository.MovieDetailRepository
import com.asad.domain.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(service = get(), mapper = get()) }
    single<MovieDetailRepository> {
        MovieDetailRepositoryImpl(
            service = get(),
            movieDetailMapper = get(),
            trailerMapper = get(),
            reviewMapper = get()
        )
    }
}