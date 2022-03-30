package com.example.ungdungnghenhac.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Playlist: Serializable {
    @SerializedName("IdPlaylist")
    @Expose
    private var idPlaylist: String? = null

    @SerializedName("Ten")
    @Expose
    private var ten: String? = null

    @SerializedName("HinhNen")
    @Expose
    private var hinhNen: String? = null

    @SerializedName("HinhIcon")
    @Expose
    private var hinhIcon: String? = null

    fun getIdPlaylist(): String? {
        return idPlaylist
    }

    fun setIdPlaylist(idPlaylist: String?) {
        this.idPlaylist = idPlaylist
    }

    fun getTen(): String? {
        return ten
    }

    fun setTen(ten: String?) {
        this.ten = ten
    }

    fun getHinhNen(): String? {
        return hinhNen
    }

    fun setHinhNen(hinhNen: String?) {
        this.hinhNen = hinhNen
    }

    fun getHinhIcon(): String? {
        return hinhIcon
    }

    fun setHinhIcon(hinhIcon: String?) {
        this.hinhIcon = hinhIcon
    }
}