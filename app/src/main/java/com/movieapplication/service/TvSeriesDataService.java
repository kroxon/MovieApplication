package com.movieapplication.service;

import com.movieapplication.model.tvseries.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TvSeriesDataService {

    // Base Url
    // https://api.themoviedb.org/3/
    //
    // End Point Url



    @GET("tv/popular")
    Call<Result> getPopularTvSeries(@Query("api_key") String apiKey);

    @GET("tv/top_rated")
    Call<Result> getTopRatedTvSeries(@Query("api_key") String apiKey);
}
