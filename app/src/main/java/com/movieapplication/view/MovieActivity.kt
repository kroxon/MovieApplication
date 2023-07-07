package com.movieapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.movieapplication.R
import com.movieapplication.databinding.ActivityMovieBinding
import com.movieapplication.model.movie.Movie

class MovieActivity : AppCompatActivity() {
    private var movie: Movie? = null
    private var activityMovieBinding: ActivityMovieBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        activityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        val i = intent
        if (i != null) {
            movie = i.getParcelableExtra("movie")
            activityMovieBinding?.movie = movie
        }
    }
}