package com.asad.data.service

import com.asad.data.model.MovieDetailResponse
import com.asad.data.model.ReviewResponse
import com.asad.data.model.TrailerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: Int?): MovieDetailResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getTrailer(@Path("movie_id") id: Int?): TrailerResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviews(@Path("movie_id") id: Int?, @Query("page") page: Int): ReviewResponse

}
