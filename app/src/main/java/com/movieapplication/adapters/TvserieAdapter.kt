package com.movieapplication.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.movieapplication.R
import com.movieapplication.databinding.TvseriesListItemBinding
import com.movieapplication.model.tvseries.Tvserie
import com.movieapplication.view.TvSerieActivity

class TvserieAdapter(private val context: Context, private val tvseriesArrayList: ArrayList<Tvserie?>?) :
    RecyclerView.Adapter<TvserieAdapter.TvserieViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvserieViewHolder {
        val tvserieListItemBinding: TvseriesListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.tvseries_list_item,
            parent,
            false
        )
        return TvserieViewHolder(tvserieListItemBinding)
    }

    override fun onBindViewHolder(holder: TvserieViewHolder, position: Int) {

        val tvserie: Tvserie? = tvseriesArrayList?.get(position)
        holder.tvseriesListItemBinding.tvserie = tvserie

    }

    override fun getItemCount(): Int {
        return tvseriesArrayList?.size!!
    }


    inner class TvserieViewHolder(tvserieListItemBinding: TvseriesListItemBinding) :
        RecyclerView.ViewHolder(tvserieListItemBinding.getRoot()) {
        val tvseriesListItemBinding: TvseriesListItemBinding

        init {
            this.tvseriesListItemBinding = tvserieListItemBinding
            tvseriesListItemBinding.getRoot().setOnClickListener(View.OnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val selectedTvserie: Tvserie? = tvseriesArrayList?.get(position)
                    val i = Intent(context, TvSerieActivity::class.java)
                    i.putExtra("tvserie", selectedTvserie)
                    context.startActivity(i)
                }
            })
        }
    }

}

