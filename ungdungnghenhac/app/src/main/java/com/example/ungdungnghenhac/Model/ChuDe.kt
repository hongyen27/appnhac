package com.example.ungdungnghenhac.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ChuDe: Serializable {
    @SerializedName("IdChuDe")
    @Expose
    private var idChuDe: String? = null

    @SerializedName("TenChuDe")
    @Expose
    private var tenChuDe: String? = null

    @SerializedName("HinhChuDe")
    @Expose
    private var hinhChuDe: String? = null

    fun getIdChuDe(): String? {
        return idChuDe
    }

    fun setIdChuDe(idChuDe: String?) {
        this.idChuDe = idChuDe
    }

    fun getTenChuDe(): String? {
        return tenChuDe
    }

    fun setTenChuDe(tenChuDe: String?) {
        this.tenChuDe = tenChuDe
    }

    fun getHinhChuDe(): String? {
        return hinhChuDe
    }

    fun setHinhChuDe(hinhChuDe: String?) {
        this.hinhChuDe = hinhChuDe
    }
}