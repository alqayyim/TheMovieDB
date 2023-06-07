package com.asad.data.service

import com.asad.data.model.GenreResponse
import com.asad.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    suspend fun getMoviesByGenre(@Query("with_genres") genres: String, @Query("page") page: Int): MovieResponse

    @GET("genre/movie/list")
    suspend fun getGenres(): GenreResponse
}
