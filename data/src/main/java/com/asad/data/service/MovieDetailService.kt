package com.asad.data.service

import com.asad.data.model.MovieDetailResponse
import com.asad.data.model.TrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: Int?): MovieDetailResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getTrailer(@Path("movie_id") id: Int?): TrailerResponse
}
