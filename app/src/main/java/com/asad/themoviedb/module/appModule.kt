package com.asad.themoviedb.module

import com.asad.data.module.mapperModule
import com.asad.data.module.repositoryModule
import com.asad.data.module.serviceModule

val appModule = listOf(
    networkModule,
    serviceModule,
    mapperModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)