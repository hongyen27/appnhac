package com.example.ungdungnghenhac.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ungdungnghenhac.Activity.DanhSachBaiHatActivity
import com.example.ungdungnghenhac.Model.TheLoai
import com.example.ungdungnghenhac.R
import com.squareup.picasso.Picasso
import java.util.*

class DanhSachTheLoaiTheoChuDeAdapter(var context: Context, var theLoaiArrayList: ArrayList<TheLoai>) :
    RecyclerView.Adapter<DanhSachTheLoaiTheoChuDeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.dong_theloaitheochude, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val theLoai: TheLoai = theLoaiArrayList[position]
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imghinhnen)
        holder.txtTenTheLoai.setText(theLoai.getTenTheLoai())
    }

    override fun getItemCount(): Int {
        return theLoaiArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imghinhnen: ImageView
        var txtTenTheLoai: TextView

        init {
            imghinhnen = itemView.findViewById(R.id.imageviewtheloaitheochude)
            txtTenTheLoai = itemView.findViewById(R.id.txttentheloaitheochude)
            itemView.setOnClickListener {
                val intent = Intent(context, DanhSachBaiHatActivity::class.java)
                intent.putExtra("idtheloai", theLoaiArrayList.get(position))
                context.startActivity(intent)
            }
        }
    }

    init {
        this.theLoaiArrayList = theLoaiArrayList
    }
}
