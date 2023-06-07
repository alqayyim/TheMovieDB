package com.asad.data.module

import com.asad.data.mapper.*
import org.koin.dsl.module

val mapperModule = module {
    factory { MovieMapper() }
    factory { MovieDetailMapper() }
    factory { TrailerMapper() }
    factory { ReviewMapper() }
    factory { GenreMapper() }
}