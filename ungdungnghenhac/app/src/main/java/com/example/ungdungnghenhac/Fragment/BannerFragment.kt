package com.example.ungdungnghenhac.Fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.ungdungnghenhac.Adapter.BannerAdapter
import com.example.ungdungnghenhac.Model.QuangCao
import com.example.ungdungnghenhac.R
import com.example.ungdungnghenhac.Service.APIService
import com.example.ungdungnghenhac.Service.Dataservice
import me.relex.circleindicator.CircleIndicator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BannerFragment: Fragment() {
    var viewv: View? = null
    var viewPager: ViewPager? = null
    var circleIndicator: CircleIndicator? = null
    var bannerAdapter: BannerAdapter? = null
    var runnable: Runnable? = null
    var handler: Handler? = null
    var currentItem = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewv = inflater.inflate(R.layout.fragment_banner, container, false)
        setControl()
        getData()
        return viewv
    }

    private fun setControl() {
        viewPager = viewv!!.findViewById(R.id.viewpager)
        circleIndicator = viewv!!.findViewById(R.id.indicatordefault)
    }

    private fun getData() {
        val dataservice: Dataservice? = APIService.getService()
        val callback: Call<List<QuangCao>>? = dataservice?.getDataBanner()
        callback!!.enqueue(object : Callback<List<QuangCao>> {
            override fun onFailure(call: Call<List<QuangCao>>, t: Throwable?) {

            }

            override fun onResponse(
                call: Call<List<QuangCao>>,
                response: Response<List<QuangCao>>
            ) {
                val banners: List<QuangCao>? = response.body()
                // Log.d("BBB", banners!!.get(0).getTenBaiHat()!!)
                //                Log.d("BBB", banners.get(0).getTenBaiHat());
                bannerAdapter = BannerAdapter(activity!!, banners)
                viewPager!!.adapter = bannerAdapter
                circleIndicator!!.setViewPager(viewPager)
                handler = Handler()
                runnable = Runnable {
                    currentItem = viewPager!!.currentItem
                    currentItem++
                    if (currentItem >= viewPager!!.adapter!!.count) {
                        currentItem = 0
                    }
                    viewPager!!.setCurrentItem(currentItem, true)
                    handler!!.postDelayed(runnable!!, 2000)
                }
                handler!!.postDelayed(runnable!!,2000)
            }

        })
    }
}
