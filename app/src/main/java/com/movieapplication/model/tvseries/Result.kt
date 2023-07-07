package com.movieapplication.model.tvseries

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
    var results: List<Tvserie?>? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null

    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null

    constructor() {}
    constructor(`in`: Parcel) {
        page = `in`.readValue(Int::class.java.classLoader) as Int?
        `in`.readList(results!!, Tvserie::class.java.classLoader)
        totalPages = `in`.readValue(Int::class.java.classLoader) as Int?
        totalResults = `in`.readValue(Int::class.java.classLoader) as Int?
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeValue(page)
        parcel.writeValue(results)
        parcel.writeValue(totalPages)
        parcel.writeValue(totalResults)
    }

    companion object {
        // Parcelable Creator
        val CREATOR: Creator<Result?> = object : Creator<Result?> {
            override fun createFromParcel(parcel: Parcel): Result? {
                return Result(parcel)
            }

            override fun newArray(i: Int): Array<Result?> {
                return arrayOfNulls(i)
            }
        }
    }

 object CREATOR : Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}