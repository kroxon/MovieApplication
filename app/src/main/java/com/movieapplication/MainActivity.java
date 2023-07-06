package com.movieapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.movieapplication.adapters.MovieAdapter;
import com.movieapplication.adapters.TvserieAdapter;
import com.movieapplication.databinding.ActivityMainBinding;
import com.movieapplication.model.movie.Movie;
import com.movieapplication.model.tvseries.Tvserie;
import com.movieapplication.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private ArrayList<Tvserie> tvseries;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private TvserieAdapter tvserieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    private MainActivityClickHandlers handlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().setTitle("Movie Pro App");

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        handlers = new MainActivityClickHandlers(this);
        activityMainBinding.setClickHandler(handlers);

        recyclerView = activityMainBinding.rvMoviesTvseries;

        getTopRatedMovies();

        swipeRefreshLayout = activityMainBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.teal_200);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                getTopRatedMovies();
            }
        });

    }

    // upcoming movies

//    private void getUpcomingMovies() {
//        mainActivityViewModel.getAllUpcomingMovies().observe(this, new Observer<List<Movie>>() {
//            @Override
//            public void onChanged(List<Movie> moviesFromLiveData) {
//                movies = (ArrayList<Movie>) moviesFromLiveData;
//                ShowOnRecyclerView();
//            }
//        });
//    }

    // popular movies
//    private void getPopularMovies() {
//        mainActivityViewModel.getAllPopularMovies().observe(this, new Observer<List<Movie>>() {
//            @Override
//            public void onChanged(List<Movie> moviesFromLiveData) {
//                movies = (ArrayList<Movie>) moviesFromLiveData;
//                ShowOnRecyclerView();
//            }
//        });
//    }

    // now playing movies
//    private void getPlayingMovies() {
//        mainActivityViewModel.getAllPlayingMovies().observe(this, new Observer<List<Movie>>() {
//            @Override
//            public void onChanged(List<Movie> moviesFromLiveData) {
//                movies = (ArrayList<Movie>) moviesFromLiveData;
//                ShowOnRecyclerView();
//            }
//        });
//    }

    // top rated movies
    private void getTopRatedMovies() {
        mainActivityViewModel.getAllTopRatedMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movies = (ArrayList<Movie>) moviesFromLiveData;
                ShowOnRecyclerViewMovies();
            }
        });
    }

    // top rated tv series
    private void getTopRatedtvseries() {
        mainActivityViewModel.getAllTopRatedTvseries().observe(this, new Observer<List<Tvserie>>() {
            @Override
            public void onChanged(List<Tvserie> moviesFromLiveData) {
                tvseries = (ArrayList<Tvserie>) moviesFromLiveData;
                ShowOnRecyclerViewTvseries();
            }
        });
    }

    private void ShowOnRecyclerViewMovies() {

        movieAdapter = new MovieAdapter(this, movies);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }

    private void ShowOnRecyclerViewTvseries() {

        tvserieAdapter = new TvserieAdapter(this, tvseries);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tvserieAdapter);
        tvserieAdapter.notifyDataSetChanged();
    }

    public class MainActivityClickHandlers {

        Context context;

        public MainActivityClickHandlers(Context context) {
            this.context = context;
        }

        public void onMovieclicked(View view) {
            Toast.makeText(getApplicationContext(), "movie", Toast.LENGTH_SHORT).show();
            getTopRatedMovies();
        }

        public void onSerieslicked(View view) {
            Toast.makeText(getApplicationContext(), "tv serie", Toast.LENGTH_SHORT).show();
            getTopRatedtvseries();
        }
    }
}