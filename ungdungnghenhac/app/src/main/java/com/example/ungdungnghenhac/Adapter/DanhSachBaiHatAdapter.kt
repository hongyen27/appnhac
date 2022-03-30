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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DanhSachBaiHatAdapter(var context: Context, var arrbaihat: ArrayList<BaiHat>) : RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.dong_danhsachbaihat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val baihat: BaiHat = arrbaihat[position]
        holder.txtcasi.setText(baihat.getCasi())
        holder.txttenbaihat.setText(baihat.getTenBaiHat())
        holder.txtindex.setText((position + 1).toString())
    }

    override fun getItemCount(): Int {
        return arrbaihat.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtindex: TextView
        var txttenbaihat: TextView
        var txtcasi: TextView
        var imgluotthich: ImageView
        init {
            txtcasi = itemView.findViewById(R.id.txttencasi)
            txtindex = itemView.findViewById(R.id.txtdanhsachindex)
            txttenbaihat = itemView.findViewById(R.id.txttenbaihat)
            imgluotthich = itemView.findViewById(R.id.imgviewluotthichdanhsachbaihat)
            //Toast.makeText(context, arrbaihat[0].getIdPlaylist(), Toast.LENGTH_LONG).show()
           /* for (i in 0 until arrbaihat.size)
            {
                //val dataservice = APIService.getService()
                if(arrbaihat[i].getIdPlaylist().equals("pl1")){
                    imgluotthich.setImageResource(R.drawable.iconloved)
                }else{
                    imgluotthich.setImageResource(R.drawable.iconlove)
                }

            }*/
            imgluotthich.setOnClickListener {
                imgluotthich.setImageResource(R.drawable.iconloved)
                val dataservice = APIService.getService()
                val callback = dataservice!!.UpdateLuotThich(
                    "1", arrbaihat.get(
                        position
                    ).getIdBaiHat()
                )
                callback.enqueue(object : Callback<String?> {
                    override fun onResponse(
                        call: Call<String?>,
                        response: Response<String?>
                    ) {
                        val result = response.body()
                        if (result == "Success") {
                            Toast.makeText(context, "Đã thích", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(
                        call: Call<String?>,
                        t: Throwable
                    ) {
                    }
                })
                imgluotthich.isEnabled = false
            }
            itemView.setOnClickListener {
                val intent = Intent(context, PlayNhacActivity::class.java)
                intent.putExtra("cakhuc", arrbaihat[position])
                context.startActivity(intent)
            }
        }
    }

    init {
        this.arrbaihat = arrbaihat
    }
}