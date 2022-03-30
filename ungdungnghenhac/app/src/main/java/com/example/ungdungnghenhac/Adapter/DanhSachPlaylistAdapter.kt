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
import com.example.ungdungnghenhac.Model.Playlist
import com.example.ungdungnghenhac.R
import com.squareup.picasso.Picasso
import java.util.*

class DanhSachPlaylistAdapter(var context: Context, var arrplaylist: ArrayList<Playlist>) : RecyclerView.Adapter<DanhSachPlaylistAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.dong_danhsachplaylist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val playlist: Playlist = arrplaylist[position]
        Picasso.with(context).load(playlist.getHinhNen()).into(holder.imghinhnen)
        holder.txttenplaylist.setText(playlist.getTen())
    }

    override fun getItemCount(): Int {
        return arrplaylist.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imghinhnen: ImageView
        var txttenplaylist: TextView

        init {
            imghinhnen = itemView.findViewById(R.id.imageviewdanhsachplaylist)
            txttenplaylist = itemView.findViewById(R.id.txtdanhsachplaylist)
            itemView.setOnClickListener {
                val intent = Intent(context, DanhSachBaiHatActivity::class.java)
                intent.putExtra("itemplaylist", arrplaylist.get(position))
                context.startActivity(intent)
            }
        }
    }

    init {
        this.arrplaylist = arrplaylist
    }
}
