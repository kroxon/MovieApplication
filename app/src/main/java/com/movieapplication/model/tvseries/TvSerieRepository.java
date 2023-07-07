package com.movieapplication.model.tvseries;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.movieapplication.R;
import com.movieapplication.model.tvseries.Result;
import com.movieapplication.service.MovieDataService;
import com.movieapplication.service.RetrofitInstance;
import com.movieapplication.service.TvSeriesDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvSerieRepository {
    private ArrayList<Tvserie> tvseries = new ArrayList<>();
    private MutableLiveData<List<Tvserie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public TvSerieRepository(Application application) {
        this.application = application;
    }


    public MutableLiveData<List<Tvserie>> getMutableLiveData(String listType) {
        TvSeriesDataService tvSeriesDataService = RetrofitInstance.getServiceTvSerie();

        Call<Result> call = tvSeriesDataService.getTopRatedTvSeries(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();

                if (result != null && result.getResults() != null) {
                    tvseries = (ArrayList<Tvserie>) result.getResults();
                    mutableLiveData.setValue(tvseries);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

}
