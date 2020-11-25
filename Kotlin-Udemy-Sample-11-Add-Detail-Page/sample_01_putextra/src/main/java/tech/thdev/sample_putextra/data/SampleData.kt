package tech.thdev.sample_putextra.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by tae-hwan on 11/18/16.
 *
 * TODO Parcelable을 생성하세요
 */

data class SampleData(val title: String?, val message: String?, val photo: Int) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(message)
        writeInt(photo)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SampleData> = object : Parcelable.Creator<SampleData> {
            override fun createFromParcel(source: Parcel): SampleData = SampleData(source)
            override fun newArray(size: Int): Array<SampleData?> = arrayOfNulls(size)
        }
    }
}