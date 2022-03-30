package com.example.ungdungnghenhac.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ungdungnghenhac.Activity.PlayNhacActivity
import com.example.ungdungnghenhac.Model.BaiHat
import com.example.ungdungnghenhac.R
import com.example.ungdungnghenhac.Service.APIService
import com.example.ungdungnghenhac.Service.Dataservice
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SearchBaiHatAdapter(var context: Context, var arrbaihat: ArrayList<BaiHat>) : RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.dong_search_bai_hat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val baiHat: BaiHat = arrbaihat[position]
        holder.txtTenbaihat.setText(baiHat.getTenBaiHat())
        holder.txtCasi.setText(baiHat.getCasi())
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imgbaihat)
    }

    override fun getItemCount(): Int {
        return arrbaihat.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTenbaihat: TextView
        var txtCasi: TextView
        var imgbaihat: ImageView
        var imgluotthich: ImageView

        init {
            txtTenbaihat = itemView.findViewById(R.id.txtsearchtenbaihat)
            txtCasi = itemView.findViewById(R.id.txtsearchtencasi)
            imgbaihat = itemView.findViewById(R.id.imageviewSearchbaihat)
            imgluotthich = itemView.findViewById(R.id.imageviewSearchluoothich)
            itemView.setOnClickListener {
                val intent = Intent(context, PlayNhacActivity::class.java)
                intent.putExtra("cakhuc", arrbaihat[position])
                context.startActivity(intent)
            }
            imgluotthich.setOnClickListener {
                imgluotthich.setImageResource(R.drawable.iconloved)
                val dataservice: Dataservice? = APIService.getService()
                val callback: Call<String> =
                    dataservice!!.UpdateLuotThich("1", arrbaihat[position].getIdBaiHat())
                callback.enqueue(object : Callback<String> {
                    override fun onResponse(
                        call: Call<String>,
                        response: Response<String>
                    ) {
                        val result = response.body()
                        if (result.equals("Success")) {
                            Toast.makeText(context, "Đã thích", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(
                        call: Call<String>,
                        t: Throwable
                    ) {
                    }
                })
                imgluotthich.isEnabled = false
            }
        }
    }

    init {
        this.arrbaihat = arrbaihat
    }
}
