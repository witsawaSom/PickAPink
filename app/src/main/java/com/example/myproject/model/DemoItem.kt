package com.example.myproject.model

import android.os.Parcel
import android.os.Parcelable
import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem


class DemoItem : AsymmetricItem {
    private var columnSpan = 0
    private var rowSpan = 0
    var position = 0
        private set

    @JvmOverloads
    constructor(columnSpan: Int = 1, rowSpan: Int = 1, position: Int = 0) {
        this.columnSpan = columnSpan
        this.rowSpan = rowSpan
        this.position = position
    }

    constructor(`in`: Parcel) {
        readFromParcel(`in`)
    }

    override fun getColumnSpan(): Int {
        return columnSpan
    }

    override fun getRowSpan(): Int {
        return rowSpan
    }

    override fun toString(): String {
        return String.format("%s: %sx%s", position, rowSpan, columnSpan)
    }

    override fun describeContents(): Int {
        return 0
    }

    private fun readFromParcel(`in`: Parcel) {
        columnSpan = `in`.readInt()
        rowSpan = `in`.readInt()
        position = `in`.readInt()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(columnSpan)
        dest.writeInt(rowSpan)
        dest.writeInt(position)
    }

    companion object {
        /* Parcelable interface implementation */
        val CREATOR: Parcelable.Creator<DemoItem?> = object : Parcelable.Creator<DemoItem?> {
            override fun createFromParcel(`in`: Parcel): DemoItem? {
                return DemoItem(`in`)
            }

            override fun newArray(size: Int): Array<DemoItem?> {
                return arrayOfNulls(size)
            }
        }
    }
}