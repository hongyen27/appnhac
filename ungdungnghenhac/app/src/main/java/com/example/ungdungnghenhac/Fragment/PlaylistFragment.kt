package com.example.ungdungnghenhac.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ungdungnghenhac.Activity.DanhSachBaiHatActivity
import com.example.ungdungnghenhac.Activity.DanhSachCacPlaylistActivity
import com.example.ungdungnghenhac.Adapter.PlaylistAdapter
import com.example.ungdungnghenhac.Model.Playlist
import com.example.ungdungnghenhac.R
import com.example.ungdungnghenhac.Service.APIService
import com.example.ungdungnghenhac.Service.Dataservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class PlaylistFragment: Fragment() {
    var viewv: View? = null
    var lvplaylist: ListView? = null
    var txttitleplaylist: TextView? = null
    var txtviewmoreplaylist:TextView? = null
    var playlistAdapter: PlaylistAdapter? = null
    var mangplaylist:ArrayList<Playlist>?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewv = inflater.inflate(R.layout.fragment_playlist, container, false)
        lvplaylist= viewv!!.findViewById(R.id.listviewplaylist)
        txttitleplaylist = viewv!!.findViewById(R.id.txttitleplaylist)
        txtviewmoreplaylist = viewv!!.findViewById(R.id.txtviewmoreplaylist)
        getData()
        txtviewmoreplaylist!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, DanhSachCacPlaylistActivity::class.java)
            startActivity(intent)
        })
        return viewv
    }

    private fun getData() {
        val dataservice: Dataservice? = APIService.getService()
        val callback: Call<List<Playlist>>? = dataservice!!.getPlaylistCurrentDay()
        callback!!.enqueue(object : Callback<List<Playlist>> {
            override fun onFailure(call: Call<List<Playlist>>, t: Throwable?) {

            }

            override fun onResponse(
                call: Call<List<Playlist>>,
                response: Response<List<Playlist>>
            ) {
                mangplaylist = response.body() as ArrayList<Playlist>?
                //Log.d("BBB", mangplaylist!!.get(0).getTen()!!)
                playlistAdapter = PlaylistAdapter(
                    activity!!,
                    android.R.layout.simple_list_item_1,
                    mangplaylist!!
                )
                lvplaylist!!.adapter = playlistAdapter
                setListViewHeightBasedOnChildren(lvplaylist!!)
                lvplaylist!!.onItemClickListener =
                    OnItemClickListener { parent, view, position, id ->
                        val intent = Intent(activity, DanhSachBaiHatActivity::class.java)
                        intent.putExtra("itemplaylist", mangplaylist!!.get(position))
                        startActivity(intent)
                    }

            }

        })
    }

    fun setListViewHeightBasedOnChildren(listView: ListView) {
        val listAdapter = listView.adapter ?: return
        var totalHeight = listView.paddingTop + listView.paddingBottom
        val desiredWidth =
            View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.AT_MOST)
        for (i in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(i, null, listView)
            if (listItem != null) {
                listItem.layoutParams =
                    RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                    )
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
                totalHeight += listItem.measuredHeight
            }
        }
        val params = listView.layoutParams
        params.height = totalHeight + listView.dividerHeight * (listAdapter.count - 1)
        listView.layoutParams = params
        listView.requestLayout()
    }
}