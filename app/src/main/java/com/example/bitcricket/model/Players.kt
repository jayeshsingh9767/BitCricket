package com.example.bitcricket.model

import android.os.Parcel
import android.os.Parcelable

data class Players(
    val name: String?,
    val type: String?,
    val run_rate: Float,
    val wickets: Int,
    val age: Int,
    val number_of_matches: Int,
    val sixes: Int,
    val fours: Int,
    val team: String?,
    val country: String?,
    val image: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeFloat(run_rate)
        parcel.writeInt(wickets)
        parcel.writeInt(age)
        parcel.writeInt(number_of_matches)
        parcel.writeInt(sixes)
        parcel.writeInt(fours)
        parcel.writeString(team)
        parcel.writeString(country)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Players> {
        override fun createFromParcel(parcel: Parcel): Players {
            return Players(parcel)
        }

        override fun newArray(size: Int): Array<Players?> {
            return arrayOfNulls(size)
        }
    }
}