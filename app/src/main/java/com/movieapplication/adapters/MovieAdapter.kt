package com.movieapplication.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.movieapplication.R
import com.movieapplication.databinding.MovieListItemBinding
import com.movieapplication.model.movie.Movie
import com.movieapplication.view.MovieActivity

class MovieAdapter(private val context: Context, private val movieArrayList: ArrayList<Movie?>?) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieListItemBinding: MovieListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.movie_list_item,
            parent,
            false
        )
        return MovieViewHolder(movieListItemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieArrayList?.get(position)
        holder.movieListItemBinding.setMovie(movie)
    }

    override fun getItemCount(): Int {
        return movieArrayList?.size!!
    }

    // View Holder Class
    inner class MovieViewHolder(movieListItemBinding: MovieListItemBinding) :
        RecyclerView.ViewHolder(movieListItemBinding.getRoot()) {
        val movieListItemBinding: MovieListItemBinding

        init {
            this.movieListItemBinding = movieListItemBinding
            movieListItemBinding.getRoot().setOnClickListener(View.OnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val selectedMovie = movieArrayList?.get(position)
                    val i = Intent(context, MovieActivity::class.java)
                    i.putExtra("movie", selectedMovie)
                    context.startActivity(i)
                }
            })
        }
    }
}