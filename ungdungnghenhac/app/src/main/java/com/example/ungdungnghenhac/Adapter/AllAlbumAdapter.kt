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
import com.example.ungdungnghenhac.Model.Album
import com.example.ungdungnghenhac.R
import com.squareup.picasso.Picasso
import java.util.*

class AllAlbumAdapter(var context: Context, var albumArrayList: ArrayList<Album>) : RecyclerView.Adapter<AllAlbumAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.dong_allalbum, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album: Album = albumArrayList[position]
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgallalbum)
        holder.txttenalbum.setText(album.getTenAlbum())
    }

    override fun getItemCount(): Int {
        return albumArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgallalbum: ImageView
        var txttenalbum: TextView

        init {
            imgallalbum = itemView.findViewById(R.id.imageviewallalbum)
            txttenalbum = itemView.findViewById(R.id.txttenallalbum)
            itemView.setOnClickListener {
                val intent = Intent(context, DanhSachBaiHatActivity::class.java)
                intent.putExtra("album", albumArrayList[position])
                context.startActivity(intent)
            }
        }
    }

    init {
        this.albumArrayList = albumArrayList
    }
}
