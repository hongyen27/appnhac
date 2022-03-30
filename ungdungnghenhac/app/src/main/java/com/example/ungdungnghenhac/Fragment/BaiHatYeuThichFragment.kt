package com.example.ungdungnghenhac.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ungdungnghenhac.Adapter.BaiHatYeuThichAdapter
import com.example.ungdungnghenhac.Model.BaiHat
import com.example.ungdungnghenhac.R
import com.example.ungdungnghenhac.Service.APIService
import com.example.ungdungnghenhac.Service.Dataservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class BaiHatYeuThichFragment: Fragment() {
    var viewv: View? = null
    var recyclerViewbaihathot: RecyclerView? = null
    var baiHatYeuThichAdapter: BaiHatYeuThichAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewv = inflater.inflate(R.layout.fragment_baihatyeuthich, container, false)
        recyclerViewbaihathot = viewv!!.findViewById(R.id.recyclerviewbaihathot)
        getData()
        return viewv
    }

    private fun getData() {
        val dataservice: Dataservice? = APIService.getService()
        val callback: Call<List<BaiHat>>? = dataservice!!.GetBaiHatHot()
        callback!!.enqueue(object : Callback<List<BaiHat>> {
            override fun onFailure(call: Call<List<BaiHat>>, t: Throwable?) {

            }

            override fun onResponse(
                call: Call<List<BaiHat>>,
                response: Response<List<BaiHat>>
            ) {
                val baiHatArrayList = response.body() as ArrayList<BaiHat>?
                //Log.d("BBB", baiHatArrayList!!.get(0).getTenBaiHat()!!);
                baiHatYeuThichAdapter = BaiHatYeuThichAdapter(activity!!, baiHatArrayList!!)
                val linearLayoutManager = LinearLayoutManager(activity)
                linearLayoutManager.orientation = RecyclerView.VERTICAL
                recyclerViewbaihathot!!.layoutManager = linearLayoutManager
                recyclerViewbaihathot!!.adapter = baiHatYeuThichAdapter
            }

        })
    }
}