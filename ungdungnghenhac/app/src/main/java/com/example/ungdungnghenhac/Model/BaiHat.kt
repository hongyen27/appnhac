package com.example.ungdungnghenhac.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaiHat() :Parcelable {
    @SerializedName("IdBaiHat")
    @Expose
    private var idBaiHat: String? = null

    @SerializedName("IdPlaylist")
    @Expose
    private var idPlaylist: String? = null

    @SerializedName("TenBaiHat")
    @Expose
    private var tenBaiHat: String? = null

    @SerializedName("HinhBaiHat")
    @Expose
    private var hinhBaiHat: String? = null

    @SerializedName("Casi")
    @Expose
    private var casi: String? = null

    @SerializedName("LinkBaiHat")
    @Expose
    private var linkBaiHat: String? = null

    @SerializedName("LuotThich")
    @Expose
    private var luotThich: String? = null

    constructor(parcel: Parcel) : this() {
        idBaiHat = parcel.readString()
        tenBaiHat = parcel.readString()
        hinhBaiHat = parcel.readString()
        casi = parcel.readString()
        linkBaiHat = parcel.readString()
        luotThich = parcel.readString()
        idPlaylist = parcel.readString()
    }


    fun getIdBaiHat(): String? {
        return idBaiHat
    }

    fun setIdBaiHat(idBaiHat: String?) {
        this.idBaiHat = idBaiHat
    }

    fun getIdPlaylist(): String? {
        return idPlaylist
    }

    fun setIdPlaylist(idPlaylist: String?) {
        this.idPlaylist = idPlaylist
    }

    fun getTenBaiHat(): String? {
        return tenBaiHat
    }

    fun setTenBaiHat(tenBaiHat: String?) {
        this.tenBaiHat = tenBaiHat
    }

    fun getHinhBaiHat(): String? {
        return hinhBaiHat
    }

    fun setHinhBaiHat(hinhBaiHat: String?) {
        this.hinhBaiHat = hinhBaiHat
    }

    fun getCasi(): String? {
        return casi
    }

    fun setCasi(casi: String?) {
        this.casi = casi
    }

    fun getLinkBaiHat(): String? {
        return linkBaiHat
    }

    fun setLinkBaiHat(linkBaiHat: String?) {
        this.linkBaiHat = linkBaiHat
    }

    fun getLuotThich(): String? {
        return luotThich
    }

    fun setLuotThich(luotThich: String?) {
        this.luotThich = luotThich
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idBaiHat)
        parcel.writeString(tenBaiHat)
        parcel.writeString(hinhBaiHat)
        parcel.writeString(casi)
        parcel.writeString(linkBaiHat)
        parcel.writeString(luotThich)
        parcel.writeString(idPlaylist)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BaiHat> {
        override fun createFromParcel(parcel: Parcel): BaiHat {
            return BaiHat(parcel)
        }

        override fun newArray(size: Int): Array<BaiHat?> {
            return arrayOfNulls(size)
        }
    }
}