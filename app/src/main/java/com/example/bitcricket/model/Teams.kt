package com.example.bitcricket.model

import android.os.Parcel
import android.os.Parcelable

data class Teams(
    val name: String?,
    val wins: Int?,
    val looses: Int?,
    val rank: Int?,
    val league: String?,
    val draw: Int?,
    val sponsor: String?,
    val player_count: Int?,
    val logo: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(wins)
        parcel.writeValue(looses)
        parcel.writeValue(rank)
        parcel.writeString(league)
        parcel.writeValue(draw)
        parcel.writeString(sponsor)
        parcel.writeValue(player_count)
        parcel.writeString(logo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Teams> {
        override fun createFromParcel(parcel: Parcel): Teams {
            return Teams(parcel)
        }

        override fun newArray(size: Int): Array<Teams?> {
            return arrayOfNulls(size)
        }
    }
}