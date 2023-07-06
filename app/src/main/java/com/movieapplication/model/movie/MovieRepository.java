package com.movieapplication.model.movie;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.movieapplication.R;
import com.movieapplication.service.MovieDataService;
import com.movieapplication.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }


    public MutableLiveData<List<Movie>> getMutableLiveData(String listType) {
        MovieDataService movieDataService = RetrofitInstance.getServiceMovie();

        Call<Result> call = null;
        if (listType.equals("upcoming"))
            call = movieDataService.getNowUpcomingMovies(application.getApplicationContext().getString(R.string.api_key));
        if (listType.equals("popular"))
            call = movieDataService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));
        if (listType.equals("now_playing"))
            call = movieDataService.getNowPlayingMovies(application.getApplicationContext().getString(R.string.api_key));
        if (listType.equals("top_rated"))
            call = movieDataService.getTopRatedMovies(application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();

                if (result != null && result.getResults() != null) {
                    movies = (ArrayList<Movie>) result.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

}
