package com.asad.data.module

import com.asad.data.mapper.MovieDetailMapper
import com.asad.data.mapper.MovieMapper
import com.asad.data.mapper.ReviewMapper
import com.asad.data.mapper.TrailerMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { MovieMapper() }
    factory { MovieDetailMapper() }
    factory { TrailerMapper() }
    factory { ReviewMapper() }
}