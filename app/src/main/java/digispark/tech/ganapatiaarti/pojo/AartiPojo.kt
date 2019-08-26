package digispark.tech.ganapatiaarti.pojo

import android.os.Parcel
import android.os.Parcelable

class AartiPojo(val name: String?, val raw: Int, val thumbnail: Int) : Parcelable {
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

    companion object CREATOR : Parcelable.Creator<AartiPojo> {
        override fun createFromParcel(parcel: Parcel): AartiPojo {
            return AartiPojo(parcel)
        }

        override fun newArray(size: Int): Array<AartiPojo?> {
            return arrayOfNulls(size)
        }
    }
}