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

class AlbumAdapter(var context: Context, var arrAlbum: ArrayList<Album>) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.dong_album, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = arrAlbum[position]
        holder.txtcasialbum.text = album.getTenCaSiAlbum()
        holder.txttenalbum.text = album.getTenAlbum()
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imghinhalbum)
    }

    override fun getItemCount(): Int {
        return arrAlbum.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imghinhalbum: ImageView
        var txttenalbum: TextView
        var txtcasialbum: TextView

        init {
            imghinhalbum = itemView.findViewById(R.id.imageviewalbum)
            txttenalbum = itemView.findViewById(R.id.txttenalbum)
            txtcasialbum = itemView.findViewById(R.id.txttencasialbum)
            itemView.setOnClickListener {
                val intent = Intent(context, DanhSachBaiHatActivity::class.java)
                intent.putExtra("album", arrAlbum[position])
                context.startActivity(intent)
            }
        }
    }
}