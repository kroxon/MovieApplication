package com.movieapplication.model.movie

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.movieapplication.R
import com.movieapplication.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val application: Application) {
    private var movies = ArrayList<Movie>()
    private val mutableLiveData = MutableLiveData<List<Movie>>()
    fun getMutableLiveData(listType: String): MutableLiveData<List<Movie>> {
        val movieDataService = RetrofitInstance.getServiceMovie()
        var call: Call<Result?>? = null
        if (listType == "upcoming") call =
            movieDataService.getNowUpcomingMovies(application.applicationContext.getString(R.string.api_key))
        if (listType == "popular") call =
            movieDataService.getPopularMovies(application.applicationContext.getString(R.string.api_key))
        if (listType == "now_playing") call =
            movieDataService.getNowPlayingMovies(application.applicationContext.getString(R.string.api_key))
        if (listType == "top_rated") call =
            movieDataService.getTopRatedMovies(application.applicationContext.getString(R.string.api_key))
        call!!.enqueue(object : Callback<Result?> {
            override fun onResponse(call: Call<Result?>, response: Response<Result?>) {
                val result = response.body()
                if (result != null && result.results != null) {
                    movies = result.results as ArrayList<Movie>
                    mutableLiveData.value = movies
                }
            }

            override fun onFailure(call: Call<Result?>, t: Throwable) {}
        })
        return mutableLiveData
    }
}