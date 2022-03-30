package com.example.ungdungnghenhac.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TheLoaiTrongNgay {
    @SerializedName("TheLoai")
    @Expose
    private var theLoai: List<TheLoai>? = null

    @SerializedName("ChuDe")
    @Expose
    private var chuDe: List<ChuDe>? = null

    fun getTheLoai(): List<TheLoai>? {
        return theLoai
    }

    fun setTheLoai(theLoai: List<TheLoai>?) {
        this.theLoai = theLoai
    }

    fun getChuDe(): List<ChuDe>? {
        return chuDe
    }

    fun setChuDe(chuDe: List<ChuDe>?) {
        this.chuDe = chuDe
    }
}