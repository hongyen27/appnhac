package com.example.ungdungnghenhac.Fragment

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ungdungnghenhac.Adapter.SearchBaiHatAdapter
import com.example.ungdungnghenhac.Model.BaiHat
import com.example.ungdungnghenhac.R
import com.example.ungdungnghenhac.Service.APIService
import com.example.ungdungnghenhac.Service.Dataservice
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Fragment_Tim_Kiem : Fragment() {
    var viewv: View? = null
    var toolbar: Toolbar? = null
    var recyclerViewsearchbaihat: RecyclerView? = null
    var txtkhongcodulieu: TextView? = null
    var searchBaiHatAdapter: SearchBaiHatAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewv = inflater.inflate(R.layout.fragment_tim_kiem, container, false)
        toolbar = viewv!!.findViewById(R.id.toolbarsearchbaihat)
        recyclerViewsearchbaihat = viewv!!.findViewById(R.id.recyclerviewsearchbaihat)
        txtkhongcodulieu = viewv!!.findViewById(R.id.txtkhongcodulieu)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        toolbar!!.setTitle("")
        setHasOptionsMenu(true)
        return viewv
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_view, menu)
        val menuItem = menu.findItem(R.id.menu_search)
        val searchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                SearchKeyBaiHat(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun SearchKeyBaiHat(query: String) {
        val dataservice: Dataservice? = APIService.getService()
        val callback: Call<List<BaiHat>> = dataservice!!.GetSearchBaiHat(query)
        callback.enqueue(object : Callback<List<BaiHat>> {
            override fun onResponse(call: Call<List<BaiHat>>, response: Response<List<BaiHat>>) {
                val arrbaihat = response.body() as ArrayList<BaiHat>
                if (arrbaihat.size > 0) {
                    searchBaiHatAdapter = SearchBaiHatAdapter(activity!!, arrbaihat)
                    val linearLayoutManager = LinearLayoutManager(activity)
                    recyclerViewsearchbaihat!!.layoutManager = linearLayoutManager
                    recyclerViewsearchbaihat!!.adapter = searchBaiHatAdapter
                    txtkhongcodulieu!!.visibility = View.GONE
                    recyclerViewsearchbaihat!!.visibility = View.VISIBLE
                } else {
                    recyclerViewsearchbaihat!!.visibility = View.GONE
                    txtkhongcodulieu!!.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<BaiHat>>, t: Throwable) {}
        })
    }
}