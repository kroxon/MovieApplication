package com.movieapplication.model.movie

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("jsonschema2pojo")
class Result : Parcelable {
    @SerializedName("page")
    @Expose
    var page: Int? = null

    @SerializedName("results")
    @Expose
    var results: List<Movie?>? = null

    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null

    constructor() {}
    constructor(`in`: Parcel) {
        page = `in`.readValue(Int::class.java.classLoader) as Int?
        totalResults = `in`.readValue(Int::class.java.classLoader) as Int?
        totalPages = `in`.readValue(Int::class.java.classLoader) as Int?
        `in`.readList(
            results!!,
            Movie::class.java.classLoader
        )
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeValue(page)
        parcel.writeValue(totalResults)
        parcel.writeValue(totalPages)
        parcel.writeList(results)
    }

    companion object {
        // Parcelable Creator
        @JvmField
        val CREATOR: Creator<Result?> = object : Creator<Result?> {
            override fun createFromParcel(parcel: Parcel): Result? {
                return Result(parcel)
            }

            override fun newArray(i: Int): Array<Result?> {
                return arrayOfNulls(i)
            }
        }
    }
}