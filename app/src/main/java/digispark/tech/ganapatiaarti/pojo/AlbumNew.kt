package digispark.tech.ganapatiaarti.pojo

import android.os.Parcel
import android.os.Parcelable

class AlbumNew(val name: String?, val raw: Int, val thumbnail: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(raw)
        parcel.writeInt(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlbumNew> {
        override fun createFromParcel(parcel: Parcel): AlbumNew {
            return AlbumNew(parcel)
        }

        override fun newArray(size: Int): Array<AlbumNew?> {
            return arrayOfNulls(size)
        }
    }
}