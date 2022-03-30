package com.example.ungdungnghenhac.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class ViewPagerPlaylistnhac(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    val fragmentArrayList = ArrayList<Fragment>()
    override fun getCount(): Int {
        return fragmentArrayList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentArrayList.get(position)
    }

    fun AddFragment(fragment: Fragment?) {
        fragmentArrayList.add(fragment!!)
    }
}