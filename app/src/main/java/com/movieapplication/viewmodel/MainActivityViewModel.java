package com.movieapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.movieapplication.model.movie.Movie;
import com.movieapplication.model.movie.MovieRepository;
import com.movieapplication.model.tvseries.TvSerieRepository;
import com.movieapplication.model.tvseries.Tvserie;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private TvSerieRepository tvSerieRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        tvSerieRepository = new TvSerieRepository(application);
    }

    // Live Data
    public LiveData<List<Movie>> getAllUpcomingMovies(){
        return movieRepository.getMutableLiveData("upcoming");
    }

    public LiveData<List<Movie>> getAllPopularMovies(){
        return movieRepository.getMutableLiveData("popular");
    }

    public LiveData<List<Movie>> getAllPlayingMovies(){
        return movieRepository.getMutableLiveData("now_playing");
    }

    public LiveData<List<Movie>> getAllTopRatedMovies(){
        return movieRepository.getMutableLiveData("top_rated");
    }

    public LiveData<List<Tvserie>> getAllTopRatedTvseries(){
        return tvSerieRepository.getMutableLiveData("top_rated");
    }
}
