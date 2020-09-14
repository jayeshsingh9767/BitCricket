package com.example.bitcricket.model

import android.os.Parcel
import android.os.Parcelable

data class Matches(
    val team1: String?,
    val team2: String?,
    val date: String?,
    val type: String?,
    val league: String?,
    val status: String?,            // pending | live | played
    val winner: String?,
    val looser: String?,
    val mom: String?,
    val bom: String?,
    val bestFielder: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(team1)
        parcel.writeString(team2)
        parcel.writeString(date)
        parcel.writeString(type)
        parcel.writeString(league)
        parcel.writeString(status)
        parcel.writeString(winner)
        parcel.writeString(looser)
        parcel.writeString(mom)
        parcel.writeString(bom)
        parcel.writeString(bestFielder)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Matches> {
        override fun createFromParcel(parcel: Parcel): Matches {
            return Matches(parcel)
        }

        override fun newArray(size: Int): Array<Matches?> {
            return arrayOfNulls(size)
        }
    }
}

