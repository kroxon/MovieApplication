package com.movieapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.movieapplication.R
import com.movieapplication.databinding.ActivityTvSerieBinding
import com.movieapplication.model.movie.Movie
import com.movieapplication.model.tvseries.Tvserie

class TvSerieActivity : AppCompatActivity() {

    private var tvSeries: Tvserie? = null
    var binding: ActivityTvSerieBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_serie)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_serie)

        val i = intent
        if (i != null) {
            tvSeries = i.getParcelableExtra<Tvserie>("tvserie")
            binding?.tvserie = tvSeries
//            supportActionBar?.title = tvSeries?.title
        }


    }

}