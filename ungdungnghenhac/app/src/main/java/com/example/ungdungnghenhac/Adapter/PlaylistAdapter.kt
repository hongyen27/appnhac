package com.example.ungdungnghenhac.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.ungdungnghenhac.Model.Playlist
import com.example.ungdungnghenhac.R
import com.squareup.picasso.Picasso

class PlaylistAdapter(context: Context, resource: Int, objects: List<Playlist?>) :
    ArrayAdapter<Playlist?>(context, resource, objects) {

    internal class ViewHolder {
        var txttenplaylist: TextView? = null
        var imgbackground: ImageView? = null
        var imgplaylist: ImageView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var viewHolder: ViewHolder? = null
        var convertView = convertView
        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.dong_playlist, null)
            viewHolder = ViewHolder()
            viewHolder.txttenplaylist = convertView!!.findViewById<TextView>(R.id.txttenplaylist)
            viewHolder.imgplaylist = convertView.findViewById<ImageView>(R.id.imageviewplaylist)
            viewHolder.imgbackground = convertView.findViewById<ImageView>(R.id.imageviewbackgroundplaylist)
            convertView.setTag(viewHolder)
        } else {
            viewHolder= convertView.getTag() as ViewHolder?
        }
        val playlist = getItem(position)
        Picasso.with(context).load(playlist!!.getHinhNen()).into(viewHolder!!.imgbackground)
        Picasso.with(context).load(playlist.getHinhIcon()).into(viewHolder.imgplaylist)
        viewHolder.txttenplaylist!!.text = playlist.getTen()
        return convertView
    }
}