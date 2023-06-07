package com.asad.data.service

import com.asad.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/{movie_path}")
    suspend fun getMovies(@Path("movie_path") path: String?, @Query("page") page: Int): MovieResponse
}
