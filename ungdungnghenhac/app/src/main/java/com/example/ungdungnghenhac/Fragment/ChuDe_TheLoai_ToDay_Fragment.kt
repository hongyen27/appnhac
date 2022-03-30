package com.example.ungdungnghenhac.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.ungdungnghenhac.Activity.DanhSachBaiHatActivity
import com.example.ungdungnghenhac.Activity.DanhSachTatCaChuDeActivity
import com.example.ungdungnghenhac.Activity.DanhSachTheLoaiTheoChuDeActivity
import com.example.ungdungnghenhac.Model.ChuDe
import com.example.ungdungnghenhac.Model.TheLoai
import com.example.ungdungnghenhac.Model.TheLoaiTrongNgay
import com.example.ungdungnghenhac.R
import com.example.ungdungnghenhac.Service.APIService
import com.example.ungdungnghenhac.Service.Dataservice
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ChuDe_TheLoai_ToDay_Fragment: Fragment() {
    var viewv: View? = null
    var horizontalScrollView: HorizontalScrollView? = null
    var txtxemthemchudetheloai: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewv = inflater.inflate(R.layout.fragment_chude_theloai_today, container, false)
        horizontalScrollView = viewv!!.findViewById(R.id.horizontalScrollview)
        txtxemthemchudetheloai = viewv!!.findViewById(R.id.txtxemthem)
        txtxemthemchudetheloai!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, DanhSachTatCaChuDeActivity::class.java)
            startActivity(intent)
        })
        getData()
        return viewv
    }

    private fun getData() {
        val dataservice: Dataservice? = APIService.getService()
        val callback: Call<TheLoaiTrongNgay>? = dataservice!!.GetCategoryMusic()
        callback!!.enqueue(object : Callback<TheLoaiTrongNgay> {
            override fun onFailure(call: Call<TheLoaiTrongNgay>, t: Throwable?) {

            }

            override fun onResponse(
                call: Call<TheLoaiTrongNgay>,
                response: Response<TheLoaiTrongNgay>
            ) {
                val theLoaiTrongNgay = response.body()

                val chuDeArrayList: ArrayList<ChuDe> = ArrayList<ChuDe>()
                chuDeArrayList.addAll(theLoaiTrongNgay!!.getChuDe()!!)

                val theLoaiArrayList: ArrayList<TheLoai> = ArrayList<TheLoai>()
                theLoaiArrayList.addAll(theLoaiTrongNgay.getTheLoai()!!)

                val linearLayout = LinearLayout(activity)
                linearLayout.orientation = LinearLayout.HORIZONTAL

                val layout = LinearLayout.LayoutParams(700, 300)
                layout.setMargins(10, 20, 10, 30)

                for (i in 0 until chuDeArrayList.size) {
                    val cardView = CardView(activity!!)
                    cardView.radius = 10f
                    val imageView = ImageView(activity)
                    imageView.scaleType = ImageView.ScaleType.FIT_XY
                    if (chuDeArrayList[i].getHinhChuDe() != null) {
                        Picasso.with(activity).load(chuDeArrayList[i].getHinhChuDe())
                            .into(imageView)
                    }
                    cardView.layoutParams = layout
                    cardView.addView(imageView)
                    linearLayout.addView(cardView)
                    imageView.setOnClickListener {
                        val intent = Intent(activity, DanhSachTheLoaiTheoChuDeActivity::class.java)
                        intent.putExtra("chude", chuDeArrayList.get(i))
                        startActivity(intent)
                    }
                }
                for (j in 0 until chuDeArrayList.size) {
                    val cardView = CardView(activity!!)
                    cardView.radius = 10f
                    val imageView = ImageView(activity)
                    imageView.scaleType = ImageView.ScaleType.FIT_XY
                    if (theLoaiArrayList[j].getHinhTheLoai() != null) {
                        Picasso.with(activity).load(theLoaiArrayList[j].getHinhTheLoai())
                            .into(imageView)
                    }
                    cardView.layoutParams = layout
                    cardView.addView(imageView)
                    linearLayout.addView(cardView)
                    imageView.setOnClickListener {
                        val intent = Intent(activity, DanhSachBaiHatActivity::class.java)
                        intent.putExtra("idtheloai", theLoaiArrayList.get(j))
                        startActivity(intent)
                    }
                }
                horizontalScrollView!!.addView(linearLayout)
            }

        })
    }
}