package com.ixzus.appkotlin.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * 功能描述:
 * Created by ixzus on 2017/8/25.
 */

class PicBean : Parcelable {
    var id: String? = null
    var url: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.id)
        dest.writeString(this.url)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.id = `in`.readString()
        this.url = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<PicBean> = object : Parcelable.Creator<PicBean> {
            override fun createFromParcel(source: Parcel): PicBean {
                return PicBean(source)
            }

            override fun newArray(size: Int): Array<PicBean?> {
                return arrayOfNulls(size)
            }
        }
    }
}
