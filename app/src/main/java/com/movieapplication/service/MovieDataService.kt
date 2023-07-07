package com.movieapplication.service

import com.movieapplication.model.movie.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieDataService {
    // Base Url
    // https://api.themoviedb.org/3/
    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String?): Call<Result?>?

    @GET("movie/upcoming")
    fun getNowUpcomingMovies(@Query("api_key") apiKey: String?): Call<Result?>?

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String?): Call<Result?>?

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String?): Call<Result?>?
}
