package com.example.ungdungnghenhac.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ungdungnghenhac.Model.BaiHat
import com.example.ungdungnghenhac.R
import java.util.*

class PlayNhacAdapter(var context: FragmentActivity?, var arrbaihat: ArrayList<BaiHat>) : RecyclerView.Adapter<PlayNhacAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.dong_playbaihat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val baiHat: BaiHat = arrbaihat[position]
        holder.txtcasi.setText(baiHat.getCasi())
        holder.txtindex.setText((position + 1 ).toString())
        holder.txttenbaihat.setText(baiHat.getTenBaiHat())
    }

    override fun getItemCount(): Int {
        return arrbaihat.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtindex: TextView
        var txttenbaihat: TextView
        var txtcasi: TextView
        init {
            txtcasi = itemView.findViewById(R.id.txtplaynhactencasi)
            txttenbaihat = itemView.findViewById(R.id.txtplaynhactenbaihat)
            txtindex = itemView.findViewById(R.id.txtplaynhacindex)
        }
    }
    init {
        this.arrbaihat = arrbaihat
    }
}