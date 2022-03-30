package com.example.ungdungnghenhac.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.ungdungnghenhac.Activity.DanhSachBaiHatActivity
import com.example.ungdungnghenhac.Model.QuangCao
import com.example.ungdungnghenhac.R
import com.squareup.picasso.Picasso

class BannerAdapter(var context: Context, var arraylistbanner: List<QuangCao>?): PagerAdapter() {

    override fun getCount(): Int {
        return arraylistbanner!!.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.dong_banner, container, false)

        val imgbackgroundbanner = view.findViewById<ImageView>(R.id.imageviewbackgroundbanner)
        val imgsongbanner = view.findViewById<ImageView>(R.id.imageviewbanner)
        val txttitlesongbanner = view.findViewById<TextView>(R.id.txttitlebannerbaihat)
        val txtnoidung = view.findViewById<TextView>(R.id.txtnoidung)

        Picasso.with(context).load(arraylistbanner!![position].getHinhAnh()).into(
            imgbackgroundbanner
        )
        Picasso.with(context).load(arraylistbanner!![position].getHinhBaiHat()).into(imgsongbanner)
        txttitlesongbanner.text = arraylistbanner!![position].getTenBaiHat()
        txtnoidung.text = arraylistbanner!![position].getNoiDung()

        view.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(context, DanhSachBaiHatActivity::class.java)
                intent.putExtra("banner", arraylistbanner!![position])
                context.startActivity(intent)
            }

        })
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}