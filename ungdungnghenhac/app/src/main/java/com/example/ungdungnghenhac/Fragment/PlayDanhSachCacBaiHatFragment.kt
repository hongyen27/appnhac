package com.example.ungdungnghenhac.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ungdungnghenhac.Activity.PlayNhacActivity
import com.example.ungdungnghenhac.Adapter.PlayNhacAdapter
import com.example.ungdungnghenhac.R

class PlayDanhSachCacBaiHatFragment:Fragment() {
    var viewv: View? = null
    var recyclerViewplaynhac: RecyclerView? = null
    var playNhacAdapter: PlayNhacAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewv = inflater.inflate(R.layout.fragment_play_danh_sach_cac_bai_hat, container, false)
        recyclerViewplaynhac = viewv!!.findViewById(R.id.recyclerviewPlaybaihat)
        playNhacAdapter = PlayNhacAdapter(activity, PlayNhacActivity.arrbaihat)
        if (PlayNhacActivity.arrbaihat.size > 0) {
            playNhacAdapter = PlayNhacAdapter(activity, PlayNhacActivity.arrbaihat)
            recyclerViewplaynhac!!.setLayoutManager(LinearLayoutManager(activity))
            recyclerViewplaynhac!!.setAdapter(playNhacAdapter)
        }
        return viewv
    }
}