package com.example.ungdungnghenhac.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ungdungnghenhac.Adapter.DanhSachTatCaChuDeAdapter
import com.example.ungdungnghenhac.Model.ChuDe
import com.example.ungdungnghenhac.R
import com.example.ungdungnghenhac.Service.APIService
import com.example.ungdungnghenhac.Service.Dataservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DanhSachTatCaChuDeActivity : AppCompatActivity() {
    var recyclerViewallchude: RecyclerView? = null
    var toolbarallChude: Toolbar? = null
    var danhSachTatCaChuDeAdapter: DanhSachTatCaChuDeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_tat_ca_chu_de)
        init();
        getData()
    }

    private fun getData() {
        val dataservice: Dataservice? = APIService.getService()
        val callback: Call<List<ChuDe>>? = dataservice!!.GetAllChuDe()
        callback!!.enqueue(object : Callback<List<ChuDe>> {
            override fun onResponse(call: Call<List<ChuDe>>, response: Response<List<ChuDe>>) {
                val arrChuDe: ArrayList<ChuDe> = response.body() as ArrayList<ChuDe>
                danhSachTatCaChuDeAdapter = DanhSachTatCaChuDeAdapter(this@DanhSachTatCaChuDeActivity, arrChuDe)
                recyclerViewallchude!!.layoutManager = GridLayoutManager(this@DanhSachTatCaChuDeActivity, 1)
                recyclerViewallchude!!.adapter = danhSachTatCaChuDeAdapter

            }

            override fun onFailure(call: Call<List<ChuDe>>, t: Throwable) {}
        })
    }

    private fun init() {
        recyclerViewallchude = findViewById<RecyclerView>(R.id.recyclerviewAllchude)
        toolbarallChude = findViewById<Toolbar>(R.id.toolbarallchude)
        setSupportActionBar(toolbarallChude)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Tất Cả Chủ Đề"
        toolbarallChude!!.setNavigationOnClickListener(View.OnClickListener { finish() })
    }
}