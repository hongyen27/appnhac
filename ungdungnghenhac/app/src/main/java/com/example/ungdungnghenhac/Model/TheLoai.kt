package com.example.ungdungnghenhac.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TheLoai:Serializable {
    @SerializedName("IdTheLoai")
    @Expose
    private var idTheLoai: String? = null

    @SerializedName("IdKeyChuDe")
    @Expose
    private var idKeyChuDe: String? = null

    @SerializedName("TenTheLoai")
    @Expose
    private var tenTheLoai: String? = null

    @SerializedName("HinhTheLoai")
    @Expose
    private var hinhTheLoai: String? = null

    fun getIdTheLoai(): String? {
        return idTheLoai
    }

    fun setIdTheLoai(idTheLoai: String?) {
        this.idTheLoai = idTheLoai
    }

    fun getIdKeyChuDe(): String? {
        return idKeyChuDe
    }

    fun setIdKeyChuDe(idKeyChuDe: String?) {
        this.idKeyChuDe = idKeyChuDe
    }

    fun getTenTheLoai(): String? {
        return tenTheLoai
    }

    fun setTenTheLoai(tenTheLoai: String?) {
        this.tenTheLoai = tenTheLoai
    }

    fun getHinhTheLoai(): String? {
        return hinhTheLoai
    }

    fun setHinhTheLoai(hinhTheLoai: String?) {
        this.hinhTheLoai = hinhTheLoai
    }
}