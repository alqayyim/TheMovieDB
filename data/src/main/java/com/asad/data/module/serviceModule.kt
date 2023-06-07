package com.asad.data.module

import com.asad.data.service.MovieDetailService
import com.asad.data.service.MovieService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    factory { get<Retrofit>().create(MovieService::class.java) }
    factory { get<Retrofit>().create(MovieDetailService::class.java) }
}