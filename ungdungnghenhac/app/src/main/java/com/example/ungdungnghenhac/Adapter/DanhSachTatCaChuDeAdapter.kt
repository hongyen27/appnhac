package com.example.ungdungnghenhac.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ungdungnghenhac.Activity.DanhSachBaiHatActivity
import com.example.ungdungnghenhac.Activity.DanhSachTheLoaiTheoChuDeActivity
import com.example.ungdungnghenhac.Model.ChuDe
import com.example.ungdungnghenhac.R
import com.squareup.picasso.Picasso
import java.util.*

class DanhSachTatCaChuDeAdapter(var context: Context,var arrChude: ArrayList<ChuDe>) : RecyclerView.Adapter<DanhSachTatCaChuDeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.dong_cacchude, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chuDe: ChuDe = arrChude[position]
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(holder.imgchude)
    }

    override fun getItemCount(): Int {
        return arrChude.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgchude: ImageView

        init {
            imgchude = itemView.findViewById(R.id.imgviewdongcacchude)
            imgchude.setOnClickListener {
                val intent = Intent(context, DanhSachTheLoaiTheoChuDeActivity::class.java)
                intent.putExtra("chude", arrChude[position])
                context.startActivity(intent)
            }
        }
    }

    init {
        this.arrChude = arrChude
    }
}
