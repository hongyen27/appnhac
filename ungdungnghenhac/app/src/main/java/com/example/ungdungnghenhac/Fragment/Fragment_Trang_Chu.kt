package com.example.ungdungnghenhac.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ungdungnghenhac.R

class Fragment_Trang_Chu : Fragment() {
    var viewv: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewv = inflater.inflate(R.layout.fragment_trang_chu, container, false)
        return viewv
    }
}