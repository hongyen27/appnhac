package com.example.ungdungnghenhac.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ungdungnghenhac.Adapter.DanhSachPlaylistAdapter
import com.example.ungdungnghenhac.Model.Playlist
import com.example.ungdungnghenhac.R
import com.example.ungdungnghenhac.Service.APIService
import com.example.ungdungnghenhac.Service.Dataservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DanhSachCacPlaylistActivity : AppCompatActivity() {
    var toolbar: Toolbar? = null
    var recyclerViewdanhsachcacplaylist: RecyclerView? = null
    var danhSachPlaylistAdapter: DanhSachPlaylistAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_cac_playlist)
        setControl()
        init()
        GetData()
    }

    private fun GetData() {
        val dataservice: Dataservice? = APIService.getService()
        val callback: Call<List<Playlist>>? = dataservice!!.GetDanhSachPlaylist()
        callback!!.enqueue(object : Callback<List<Playlist>> {
            override fun onResponse(
                call: Call<List<Playlist>>,
                response: Response<List<Playlist>>
            ) {
                val arrplaylist: ArrayList<Playlist> = response.body() as ArrayList<Playlist>
                danhSachPlaylistAdapter = DanhSachPlaylistAdapter(this@DanhSachCacPlaylistActivity, arrplaylist)
                recyclerViewdanhsachcacplaylist!!.layoutManager = GridLayoutManager(this@DanhSachCacPlaylistActivity, 2)
                recyclerViewdanhsachcacplaylist!!.adapter = danhSachPlaylistAdapter
            }

            override fun onFailure(call: Call<List<Playlist>>, t: Throwable) {}
        })
    }

    private fun setControl() {
        toolbar = findViewById(R.id.toolbardanhsachcacplaylist)
        recyclerViewdanhsachcacplaylist = findViewById(R.id.recyclerviewdanhsachcacplaylist)
    }

    private  fun init(){
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Playlists"
        toolbar!!.setTitleTextColor(resources.getColor(R.color.violet))
        toolbar!!.setNavigationOnClickListener { finish() }
    }
}