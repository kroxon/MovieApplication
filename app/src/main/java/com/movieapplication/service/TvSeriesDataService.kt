package com.movieapplication.service

import com.movieapplication.model.tvseries.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TvSeriesDataService {
    // Base Url
    // https://api.themoviedb.org/3/
    //
    // End Point Url
    @GET("tv/popular")
    fun getPopularTvSeries(@Query("api_key") apiKey: String?): Call<Result?>?

    @GET("tv/top_rated")
    fun getTopRatedTvSeries(@Query("api_key") apiKey: String?): Call<Result?>?
}
