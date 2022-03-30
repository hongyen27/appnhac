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

class BaiHatYeuThichAdapter(var context: Context, var baiHatArrayList: ArrayList<BaiHat>) : RecyclerView.Adapter<BaiHatYeuThichAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.dong_baihatyeuthich, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val baihat: BaiHat = baiHatArrayList[position]
        holder.txtcasi.setText(baihat.getCasi())
        holder.txtten.setText(baihat.getTenBaiHat())
        Picasso.with(context).load(baihat.getHinhBaiHat()).into(holder.imghinh)
    }

    override fun getItemCount(): Int {
        return baiHatArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtten: TextView
        var txtcasi: TextView
        var imghinh: ImageView
        var imgluotthich: ImageView

        init {
            txtten = itemView.findViewById(R.id.txttenbaihatyeuthich)
            txtcasi = itemView.findViewById(R.id.txtcasibaihatyeuthich)
            imghinh = itemView.findViewById(R.id.imageviewbaihatyeuthich)
            imgluotthich = itemView.findViewById(R.id.imageviewluotthich)
            imgluotthich.setOnClickListener {
                imgluotthich.setImageResource(R.drawable.iconloved)
                val dataservice: Dataservice? = APIService.getService()
                val callback: Call<String> = dataservice!!.UpdateLuotThich(
                    "1",
                    baiHatArrayList[position].getIdBaiHat()
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

                    override fun onFailure(call: Call<String?>, t: Throwable) {}
                })
                imgluotthich.isEnabled = false
            }
            itemView.setOnClickListener {
                val intent = Intent(context, PlayNhacActivity::class.java)
                intent.putExtra("cakhuc", baiHatArrayList[position])
                context.startActivity(intent)
            }
        }
    }

    init {
        this.baiHatArrayList = baiHatArrayList
    }
}