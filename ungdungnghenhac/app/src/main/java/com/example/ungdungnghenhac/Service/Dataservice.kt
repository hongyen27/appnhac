package com.example.ungdungnghenhac.Service

import com.example.ungdungnghenhac.Model.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface Dataservice {
    @GET("songbanner.php")
    fun getDataBanner(): Call<List<QuangCao>>?

    @GET("playlistforcurrentday.php")
    fun getPlaylistCurrentDay(): Call<List<Playlist>>?

    @GET("chudevatheloaitrongngay.php")
    fun GetCategoryMusic(): Call<TheLoaiTrongNgay>?

    @GET("albumhot.php")
    fun GetAlbumHot(): Call<List<Album>>?

    @GET("baihatyeuthich.php")
    fun GetBaiHatHot(): Call<List<BaiHat>>?

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    fun GetDanhSachBaiHatTheoQuangCao(@Field("idquangcao") idquangcao: String?): Call<List<BaiHat>>?

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    fun GetDanhSachBaiHatTheoPlaylist(@Field("idplaylist") idplaylist: String?): Call<List<BaiHat>>?

    @GET("danhsachcacplaylist.php")
    fun GetDanhSachPlaylist(): Call<List<Playlist>>?

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    fun GetDanhSachBaiHatTheoTheLoai(@Field("idtheloai") idtheloai: String?): Call<List<BaiHat>>?

    @GET("tatcachude.php")
    fun GetAllChuDe(): Call<List<ChuDe>>?

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    fun GetTheLoaiTheoChuDe(@Field("idchude") idchude: String?): Call<List<TheLoai>>?

    @GET("tatcaalbum.php")
    fun GetAllAlbum(): Call<List<Album>>?

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    fun GetDanhSachBaiHatTheoAlbum(@Field("idalbum") idalbum: String?): Call<List<BaiHat>>?

    @FormUrlEncoded
    @POST("updateluotthich.php")
    fun UpdateLuotThich(
        @Field("luotthich") luotthich: String?,
        @Field("idbaihat") idbaihat: String?
    ): Call<String>

    @FormUrlEncoded
    @POST("searchbaihat.php")
    fun GetSearchBaiHat(@Field("key") key: String?): Call<List<BaiHat>>
}