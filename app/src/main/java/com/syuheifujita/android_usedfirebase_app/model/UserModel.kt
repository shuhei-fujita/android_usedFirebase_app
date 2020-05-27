package com.syuheifujita.android_usedfirebase_app.model

import android.os.Parcel
import android.os.Parcelable

data class UserModel(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val image: String = "",
    val moblile: Long = 0,
    val fcmToken: String = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readString()!!
    )

    override fun writeToParcel(dist: Parcel, flags: Int) = with(dist) {
        writeString(id)
        writeString(name)
        writeString(email)
        writeString(image)
        writeLong(moblile)
        writeString(fcmToken)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}
