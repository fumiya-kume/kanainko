package jp.ac.daido.kanainko.result.view.navigation

import android.os.Parcel
import android.os.Parcelable

data class AudioRecordResultNavigationModel(
    val filePath: String
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString() ?: "") {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(filePath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AudioRecordResultNavigationModel> {
        override fun createFromParcel(parcel: Parcel): AudioRecordResultNavigationModel {
            return AudioRecordResultNavigationModel(parcel)
        }

        override fun newArray(size: Int): Array<AudioRecordResultNavigationModel?> {
            return arrayOfNulls(size)
        }
    }
}