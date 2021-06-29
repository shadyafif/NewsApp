package com.example.newsapp.Data.Model

import android.os.Parcel
import android.os.Parcelable

data class Main(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Article)!!,
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(articles)
        parcel.writeString(status)
        parcel.writeInt(totalResults)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Main> {
        override fun createFromParcel(parcel: Parcel): Main {
            return Main(parcel)
        }

        override fun newArray(size: Int): Array<Main?> {
            return arrayOfNulls(size)
        }
    }
}