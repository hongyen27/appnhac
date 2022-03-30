package com.example.ungdungnghenhac.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ungdungnghenhac.Activity.DanhSachTatCaAlbumActivity
import com.example.ungdungnghenhac.Adapter.AlbumAdapter
import com.example.ungdungnghenhac.Model.Album
import com.example.ungdungnghenhac.R
import com.example.ungdungnghenhac.Service.APIService
import com.example.ungdungnghenhac.Service.Dataservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Album_Hot_Fragment: Fragment() {
    var viewv: View? = null
    var recyclerViewAlbum: RecyclerView? = null
    var txtxemthemalbum: TextView? = null
    var albumAdapter: AlbumAdapter? = null
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewv = inflater.inflate(R.layout.fragment_album_hot, container, false)
        recyclerViewAlbum = viewv!!.findViewById(R.id.recyclerviewAlbum)
        txtxemthemalbum = viewv!!.findViewById(R.id.txtxemthemalbum)
        txtxemthemalbum!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, DanhSachTatCaAlbumActivity::class.java)
            startActivity(intent)
        })
        getData()
        return viewv
    }

    private fun getData() {
        val dataservice: Dataservice? = APIService.getService()
        val callback: Call<List<Album>>? = dataservice!!.GetAlbumHot()
        callback!!.enqueue(object : Callback<List<Album>> {
            override fun onFailure(call: Call<List<Album>>, t: Throwable?) {

            }

            override fun onResponse(
                    call: Call<List<Album>>,
                    response: Response<List<Album>>
            ) {
                val albumArrayList = response.body() as ArrayList<Album>
                //Log.d("BBB", albumArrayList.get(0).getTenAlbum()!!);
                albumAdapter = AlbumAdapter(activity!!, albumArrayList)
                val linearLayoutManager = LinearLayoutManager(activity)
                linearLayoutManager.orientation = RecyclerView.HORIZONTAL
                recyclerViewAlbum!!.layoutManager = linearLayoutManager
                recyclerViewAlbum!!.adapter = albumAdapter

            }

        })
    }
}