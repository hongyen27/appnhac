package com.example.ungdungnghenhac.Activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ungdungnghenhac.Adapter.DanhSachBaiHatAdapter
import com.example.ungdungnghenhac.Model.*
import com.example.ungdungnghenhac.R
import com.example.ungdungnghenhac.Service.APIService
import com.example.ungdungnghenhac.Service.Dataservice
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class DanhSachBaiHatActivity : AppCompatActivity() {
    var quangcao: QuangCao? = null
    var coordinatorLayout: CoordinatorLayout? = null
    var collapsingToolbarLayout: CollapsingToolbarLayout? = null
    var toolbar: Toolbar? = null
    var recyclerViewdanhsachbaihat: RecyclerView? = null
    var floatingActionButton: FloatingActionButton? = null
    var imgdanhsachbaihat: ImageView? = null
    var arrbaihat: ArrayList<BaiHat>? = null
    var danhSachBaiHatAdapter: DanhSachBaiHatAdapter? = null
    var playlist: Playlist? = null
    var theloai: TheLoai? = null
    var album: Album? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danh_sach_bai_hat)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        DataIntent()
        setControl()
        init()

        if (quangcao != null && !quangcao!!.getTenBaiHat().equals("")) {
            setValueInView(quangcao!!.getTenBaiHat(), quangcao!!.getHinhBaiHat())
            GetDataQuangCao(quangcao!!.getIdQuangCao())
        }
        if (playlist != null && !playlist!!.getTen().equals("")) {
            setValueInView(playlist!!.getTen(), playlist!!.getHinhNen())
            GetDataPlaylist(playlist!!.getIdPlaylist())
        }
        if (theloai != null && !theloai!!.getTenTheLoai().equals("")) {
            setValueInView(theloai!!.getTenTheLoai(), theloai!!.getHinhTheLoai())
            GetDataTheLoai(theloai!!.getIdTheLoai())
        }
        if (album != null && !album!!.getTenAlbum().equals("")) {
            setValueInView(album!!.getTenAlbum(), album!!.getHinhAlbum())
            GetDataAlbum(album!!.getIdAlbum())
        }
    }

    private fun GetDataAlbum(idalbum: String?) {
        val dataservice = APIService.getService()
        val callback: Call<List<BaiHat>>? = dataservice!!.GetDanhSachBaiHatTheoAlbum(idalbum)
        callback!!.enqueue(object : Callback<List<BaiHat>> {
            override fun onResponse(call: Call<List<BaiHat>>, response: Response<List<BaiHat>>) {
                arrbaihat = response.body() as ArrayList<BaiHat>
                danhSachBaiHatAdapter = DanhSachBaiHatAdapter(this@DanhSachBaiHatActivity, arrbaihat!!)
                recyclerViewdanhsachbaihat!!.layoutManager = LinearLayoutManager(this@DanhSachBaiHatActivity)
                recyclerViewdanhsachbaihat!!.adapter = danhSachBaiHatAdapter
                eventClick()
            }

            override fun onFailure(call: Call<List<BaiHat>>, t: Throwable) {}
        })
    }

    private fun GetDataTheLoai(idtheloai: String?) {

        val dataservice = APIService.getService()
        val callback = dataservice!!.GetDanhSachBaiHatTheoTheLoai(idtheloai)
        callback!!.enqueue(object : Callback<List<BaiHat>> {
            override fun onResponse(call: Call<List<BaiHat>>, response: Response<List<BaiHat>>) {
                arrbaihat = response.body() as ArrayList<BaiHat>
                danhSachBaiHatAdapter = DanhSachBaiHatAdapter(
                    this@DanhSachBaiHatActivity,
                    arrbaihat!!
                )
                recyclerViewdanhsachbaihat!!.layoutManager =
                    LinearLayoutManager(this@DanhSachBaiHatActivity)
                recyclerViewdanhsachbaihat!!.adapter = danhSachBaiHatAdapter
                eventClick()
            }

            override fun onFailure(call: Call<List<BaiHat>>, t: Throwable) {}
        })

    }

    private fun GetDataPlaylist(idplaylist: String?) {
        val dataservice = APIService.getService()
        val callback = dataservice!!.GetDanhSachBaiHatTheoPlaylist(idplaylist)
        callback!!.enqueue(object : Callback<List<BaiHat>> {
            override fun onResponse(call: Call<List<BaiHat>>, response: Response<List<BaiHat>>) {
                arrbaihat = response.body() as ArrayList<BaiHat>
                danhSachBaiHatAdapter = DanhSachBaiHatAdapter(this@DanhSachBaiHatActivity, arrbaihat!!)
                recyclerViewdanhsachbaihat!!.layoutManager = LinearLayoutManager(this@DanhSachBaiHatActivity)
                recyclerViewdanhsachbaihat!!.adapter = danhSachBaiHatAdapter
                eventClick()
            }

            override fun onFailure(call: Call<List<BaiHat>>, t: Throwable) {}
        })
    }

    private fun GetDataQuangCao(idquangcao: String?) {
        val dataservice: Dataservice? = APIService.getService()
        val callback: Call<List<BaiHat>>? = dataservice?.GetDanhSachBaiHatTheoQuangCao(idquangcao)
        callback!!.enqueue(object : Callback<List<BaiHat>> {
            override fun onFailure(call: Call<List<BaiHat>>, t: Throwable?) {}
            override fun onResponse(call: Call<List<BaiHat>>, response: Response<List<BaiHat>>) {
                arrbaihat = response.body() as ArrayList<BaiHat>?
                Log.d("BBB", arrbaihat!!.get(0).getTenBaiHat()!!)
                danhSachBaiHatAdapter = DanhSachBaiHatAdapter(
                    this@DanhSachBaiHatActivity,
                    arrbaihat!!
                )
                recyclerViewdanhsachbaihat!!.layoutManager =
                    LinearLayoutManager(this@DanhSachBaiHatActivity)
                recyclerViewdanhsachbaihat!!.adapter = danhSachBaiHatAdapter
                eventClick()
            }

        })
    }

    private fun setValueInView(ten: String?, hinh: String?) {
        collapsingToolbarLayout!!.title = ten
        try {
            val url = URL(hinh)
            val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            val bitmapDrawable = BitmapDrawable(resources, bitmap)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout!!.background = bitmapDrawable
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Picasso.with(this).load(hinh).into(imgdanhsachbaihat)
    }

    private fun init(){
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar!!.setNavigationOnClickListener { finish() }
        collapsingToolbarLayout!!.setExpandedTitleColor(Color.WHITE)
        collapsingToolbarLayout!!.setCollapsedTitleTextColor(Color.WHITE)
        floatingActionButton!!.isEnabled = false
    }

    private fun setControl() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout)
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar)
        toolbar = findViewById(R.id.toobardanhsach)
        recyclerViewdanhsachbaihat = findViewById(R.id.recyclerviewdanhsacgbaihat)
        floatingActionButton = findViewById(R.id.floatingactionbutton)
        imgdanhsachbaihat = findViewById<ImageView>(R.id.imageviewdanhsachcakhuc)
    }

    private fun DataIntent() {
        val intent = intent
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangcao = intent.getSerializableExtra("banner") as QuangCao?
                Toast.makeText(this, quangcao!!.getTenBaiHat(), Toast.LENGTH_LONG).show()
            }
        }
        if (intent.hasExtra("itemplaylist")) {
            playlist = intent.getSerializableExtra("itemplaylist") as Playlist?
            Toast.makeText(this, playlist!!.getTen(), Toast.LENGTH_LONG).show()
        }
        if (intent.hasExtra("idtheloai")) {
            theloai = intent.getSerializableExtra("idtheloai") as TheLoai?
            Toast.makeText(this, theloai!!.getTenTheLoai(), Toast.LENGTH_LONG).show()
        }
        if (intent.hasExtra("album")) {
            album = intent.getSerializableExtra("album") as Album?
            Toast.makeText(this, album!!.getTenAlbum(), Toast.LENGTH_LONG).show()
        }
    }

    private fun eventClick() {
        floatingActionButton!!.isEnabled = true
        floatingActionButton!!.setOnClickListener {
            val intent = Intent(this@DanhSachBaiHatActivity, PlayNhacActivity::class.java)
            intent.putExtra("cacbaihat", arrbaihat)
            startActivity(intent)
        }
    }


}
