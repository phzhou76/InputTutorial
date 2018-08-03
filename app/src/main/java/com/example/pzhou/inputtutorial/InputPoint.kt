package com.example.pzhou.inputtutorial

import android.os.Parcel
import android.os.Parcelable

class InputPoint(xCoordinate: Float, yCoordinate: Float) : Parcelable
{
    var mXCoordinate: Float = xCoordinate
    var mYCoordinate: Float = yCoordinate

    private constructor(parcel: Parcel) : this(
            parcel.readFloat() /* mXCoordinate */,
            parcel.readFloat() /* mYCoordinate */
    )

    override fun writeToParcel(parcel: Parcel, flags: Int)
    {
        parcel.writeFloat(mXCoordinate)
        parcel.writeFloat(mYCoordinate)
    }

    override fun describeContents(): Int
    {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InputPoint>
    {
        override fun createFromParcel(parcel: Parcel): InputPoint
        {
            return InputPoint(parcel)
        }

        override fun newArray(size: Int): Array<InputPoint?>
        {
            return arrayOfNulls(size)
        }
    }
}