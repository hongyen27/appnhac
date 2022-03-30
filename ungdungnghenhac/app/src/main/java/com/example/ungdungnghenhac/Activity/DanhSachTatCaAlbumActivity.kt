package com.example.ungdungnghenhac.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ungdungnghenhac.Adapter.AllAlbumAdapter
import com.example.ungdungnghenhac.Model.Album
import com.example.ungdungnghenhac.R
import com.example.ungdungnghenhac.Service.APIService
import com.example.ungdungnghenhac.Service.Dataservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DanhSachTatCaAlbumActivity : AppCompatActivity() {
    var recyclerViewAllAlbum: RecyclerView? = null
    var toolbaralbum: Toolbar? = null
    var allAlbumAdapter: AllAlbumAdapter? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_tat_ca_album)
        init()
        GetData()
    }

    private fun GetData() {
        val dataservice: Dataservice? = APIService.getService()
        val callback: Call<List<Album>>? = dataservice!!.GetAllAlbum()
        callback!!.enqueue(object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                val arralbum: ArrayList<Album> = response.body() as ArrayList<Album>
                //Log.d("BBB", arralbum.get(0).getTenAlbum());
                allAlbumAdapter = AllAlbumAdapter(this@DanhSachTatCaAlbumActivity, arralbum)
                recyclerViewAllAlbum!!.layoutManager = GridLayoutManager(this@DanhSachTatCaAlbumActivity, 2)
                recyclerViewAllAlbum!!.adapter = allAlbumAdapter
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {}
        })
    }

    private fun init(){
        recyclerViewAllAlbum = findViewById(R.id.recyclerviewAllAlbum)
        toolbaralbum = findViewById(R.id.toolbaralbum)
        setSupportActionBar(toolbaralbum)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Tất Cả Album"
        toolbaralbum!!.setNavigationOnClickListener(View.OnClickListener { finish() })
    }
}