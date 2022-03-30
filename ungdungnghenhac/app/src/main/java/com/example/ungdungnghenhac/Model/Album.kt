package com.example.ungdungnghenhac.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Album: Serializable {
    @SerializedName("IdAlbum")
    @Expose
    private var idAlbum: String? = null

    @SerializedName("TenAlbum")
    @Expose
    private var tenAlbum: String? = null

    @SerializedName("TenCaSiAlbum")
    @Expose
    private var tenCaSiAlbum: String? = null

    @SerializedName("HinhAlbum")
    @Expose
    private var hinhAlbum: String? = null

    fun getIdAlbum(): String? {
        return idAlbum
    }

    fun setIdAlbum(idAlbum: String?) {
        this.idAlbum = idAlbum
    }

    fun getTenAlbum(): String? {
        return tenAlbum
    }

    fun setTenAlbum(tenAlbum: String?) {
        this.tenAlbum = tenAlbum
    }

    fun getTenCaSiAlbum(): String? {
        return tenCaSiAlbum
    }

    fun setTenCaSiAlbum(tenCaSiAlbum: String?) {
        this.tenCaSiAlbum = tenCaSiAlbum
    }

    fun getHinhAlbum(): String? {
        return hinhAlbum
    }

    fun setHinhAlbum(hinhAlbum: String?) {
        this.hinhAlbum = hinhAlbum
    }
}