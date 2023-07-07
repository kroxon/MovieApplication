package com.movieapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.movieapplication.model.movie.Movie
import com.movieapplication.model.movie.MovieRepository
import com.movieapplication.model.tvseries.TvSerieRepository
import com.movieapplication.model.tvseries.Tvserie

class MainActivityViewModel(application: Application) :
    AndroidViewModel(application) {
    private val movieRepository: MovieRepository
    private val tvSerieRepository: TvSerieRepository

    init {
        movieRepository = MovieRepository(application)
        tvSerieRepository = TvSerieRepository(application)
    }

    // Live Data
    val allUpcomingMovies: LiveData<List<Movie>>
        get() = movieRepository.getMutableLiveData("upcoming")

    val allPopularMovies: LiveData<List<Movie>>
        get() = movieRepository.getMutableLiveData("popular")
    val allPlayingMovies: LiveData<List<Movie>>
        get() = movieRepository.getMutableLiveData("now_playing")
    val allTopRatedMovies: LiveData<List<Movie>>
        get() = movieRepository.getMutableLiveData("top_rated")
    val allTopRatedTvseries: LiveData<List<Tvserie>>
        get() = tvSerieRepository.getMutableLiveData("top_rated")
}