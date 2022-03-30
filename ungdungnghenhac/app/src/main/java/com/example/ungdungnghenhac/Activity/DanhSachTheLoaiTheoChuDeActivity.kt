package com.example.ungdungnghenhac.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ungdungnghenhac.Adapter.DanhSachTheLoaiTheoChuDeAdapter
import com.example.ungdungnghenhac.Model.ChuDe
import com.example.ungdungnghenhac.Model.TheLoai
import com.example.ungdungnghenhac.R
import com.example.ungdungnghenhac.Service.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DanhSachTheLoaiTheoChuDeActivity : AppCompatActivity() {
    var chuDe: ChuDe? = null
    var recyclerViewtheloaitheochude: RecyclerView? = null
    var toolbartheloaitheochude: Toolbar? = null
    var danhSachTheLoaiTheoChuDeAdapter: DanhSachTheLoaiTheoChuDeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_the_loai_theo_chu_de)
        GetIntent()
        init()
        GetData()
    }

    private fun GetData() {
        val dataservice = APIService.getService()
        val callback: Call<List<TheLoai>>? = dataservice!!.GetTheLoaiTheoChuDe(chuDe!!.getIdChuDe()
        )
        callback!!.enqueue(object : Callback<List<TheLoai>> {
            override fun onResponse(call: Call<List<TheLoai>>, response: Response<List<TheLoai>>) {
                val arrTheLoai: ArrayList<TheLoai> = response.body() as ArrayList<TheLoai>
                //Log.d("BBB", arrTheLoai.get(0).getTenTheLoai()!!);
                danhSachTheLoaiTheoChuDeAdapter = DanhSachTheLoaiTheoChuDeAdapter(this@DanhSachTheLoaiTheoChuDeActivity, arrTheLoai)
                recyclerViewtheloaitheochude!!.layoutManager = GridLayoutManager(this@DanhSachTheLoaiTheoChuDeActivity, 2)
                recyclerViewtheloaitheochude!!.adapter = danhSachTheLoaiTheoChuDeAdapter
            }

            override fun onFailure(call: Call<List<TheLoai>>, t: Throwable) {}
        })
    }

    private fun GetIntent() {
        val intent = intent
        if (intent.hasExtra("chude")) {
            chuDe = intent.getSerializableExtra("chude") as ChuDe?
            Toast.makeText(this, chuDe!!.getTenChuDe(), Toast.LENGTH_LONG).show()
        }
    }
    private fun init(){
        recyclerViewtheloaitheochude = findViewById(R.id.recyclerviewtheloaitheochude)
        toolbartheloaitheochude = findViewById(R.id.toolbartheloaitheochude)
        setSupportActionBar(toolbartheloaitheochude)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = chuDe!!.getTenChuDe()
        toolbartheloaitheochude!!.setNavigationOnClickListener(View.OnClickListener { finish() })
    }
}