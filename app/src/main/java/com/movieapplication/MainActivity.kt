package com.movieapplication


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.movieapplication.adapters.MovieAdapter
import com.movieapplication.adapters.TvserieAdapter
import com.movieapplication.databinding.ActivityMainBinding
import com.movieapplication.model.movie.Movie
import com.movieapplication.model.tvseries.Tvserie
import com.movieapplication.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {
    private var movies: ArrayList<Movie?>? = null
    private var tvseries: ArrayList<Tvserie?>? = null
    private var recyclerView: RecyclerView? = null
    private var movieAdapter: MovieAdapter? = null
    private var tvserieAdapter: TvserieAdapter? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var mainActivityViewModel: MainActivityViewModel? = null
//    private val activityMainBinding: ActivityMainBinding? = null
    private var handlers: MainActivityClickHandlers? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        getSupportActionBar().setTitle("Movie Pro App");
        val activityMainBinding: ActivityMainBinding? = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this).get(
            MainActivityViewModel::class.java
        )
        handlers = MainActivityClickHandlers(this)
        activityMainBinding?.setClickHandler(handlers)
        recyclerView = activityMainBinding?.rvMoviesTvseries
        topRatedMovies
        swipeRefreshLayout = activityMainBinding?.swipeLayout
        swipeRefreshLayout!!.setColorSchemeResources(R.color.teal_200)
        swipeRefreshLayout!!.setOnRefreshListener {
            //                getTopRatedMovies();
        }
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
    val topRatedMovies: Unit
        get() {
            mainActivityViewModel!!.allTopRatedMovies.observe(
                this,
                object : Observer<List<Movie?>?> {
                    override fun onChanged(value: List<Movie?>?) {
                        movies = value as ArrayList<Movie?>
                        ShowOnRecyclerViewMovies()
                    }
                })
        }

    // top rated tv series
    private fun getTopRatedtvseries() {
        mainActivityViewModel!!.allTopRatedTvseries.observe(
            this,
            object : Observer<List<Tvserie?>?> {
                override fun onChanged(moviesFromLiveData: List<Tvserie?>?) {
                    tvseries = moviesFromLiveData as ArrayList<Tvserie?>
                    ShowOnRecyclerViewTvseries()
                }
            })
    }

    private fun ShowOnRecyclerViewMovies() {
        movieAdapter = MovieAdapter(this, movies)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView!!.layoutManager = GridLayoutManager(this, 2)
        } else {
            recyclerView!!.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = movieAdapter
        movieAdapter!!.notifyDataSetChanged()
    }

    private fun ShowOnRecyclerViewTvseries() {
        tvserieAdapter = TvserieAdapter(this, tvseries)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView!!.layoutManager = GridLayoutManager(this, 2)
        } else {
            recyclerView!!.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = tvserieAdapter
        tvserieAdapter!!.notifyDataSetChanged()
    }

    inner class MainActivityClickHandlers(var context: Context) {
        fun onMovieclicked(view: View?) {
            Toast.makeText(applicationContext, "movie", Toast.LENGTH_SHORT).show()
            topRatedMovies
        }

        fun onSerieslicked(view: View?) {
            Toast.makeText(applicationContext, "tv serie", Toast.LENGTH_SHORT).show()
            getTopRatedtvseries()
        }
    }
}