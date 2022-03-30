package com.example.ungdungnghenhac.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class QuangCao:Serializable {
    @SerializedName("IdQuangCao")
    @Expose
    private var idQuangCao: String? = null

    @SerializedName("HinhAnh")
    @Expose
    private var hinhAnh: String? = null

    @SerializedName("NoiDung")
    @Expose
    private var noiDung: String? = null

    @SerializedName("IdBaiHat")
    @Expose
    private var idBaiHat: String? = null

    @SerializedName("TenBaiHat")
    @Expose
    private var tenBaiHat: String? = null

    @SerializedName("HinhBaiHat")
    @Expose
    private var hinhBaiHat: String? = null

    fun getIdQuangCao(): String? {
        return idQuangCao
    }

    fun setIdQuangCao(idQuangCao: String?) {
        this.idQuangCao = idQuangCao
    }

    fun getHinhAnh(): String? {
        return hinhAnh
    }

    fun setHinhAnh(hinhAnh: String?) {
        this.hinhAnh = hinhAnh
    }

    fun getNoiDung(): String? {
        return noiDung
    }

    fun setNoiDung(noiDung: String?) {
        this.noiDung = noiDung
    }

    fun getIdBaiHat(): String? {
        return idBaiHat
    }

    fun setIdBaiHat(idBaiHat: String?) {
        this.idBaiHat = idBaiHat
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
}