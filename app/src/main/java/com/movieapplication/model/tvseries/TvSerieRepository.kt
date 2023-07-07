package com.movieapplication.model.tvseries

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.movieapplication.R
import com.movieapplication.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TvSerieRepository(private val application: Application) {
    private var tvseries = ArrayList<Tvserie>()
    private val mutableLiveData = MutableLiveData<List<Tvserie>>()
    fun getMutableLiveData(listType: String?): MutableLiveData<List<Tvserie>> {
        val tvSeriesDataService = RetrofitInstance.getServiceTvSerie()
        val call = tvSeriesDataService.getTopRatedTvSeries(
            application.applicationContext.getString(R.string.api_key)
        )
        call!!.enqueue(object : Callback<Result?> {
            override fun onResponse(call: Call<Result?>, response: Response<Result?>) {
                val result = response.body()
                if (result != null && result.results != null) {
                    tvseries = result.results as ArrayList<Tvserie>
                    mutableLiveData.value = tvseries
                }
            }

            override fun onFailure(call: Call<Result?>, t: Throwable) {}
        })
        return mutableLiveData
    }
}