package com.example.wisata_lembah_baliem.Model

import android.os.Parcel
import android.os.Parcelable


data class ModelWisata(
    val gambarWisata:Int,
    val namaWisata:String,
    val descWisata: String
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(gambarWisata)
        parcel.writeString(namaWisata)
        parcel.writeString(descWisata)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelWisata> {
        override fun createFromParcel(parcel: Parcel): ModelWisata {
            return ModelWisata(parcel)
        }

        override fun newArray(size: Int): Array<ModelWisata?> {
            return arrayOfNulls(size)
        }
    }

}