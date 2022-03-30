package com.example.ungdungnghenhac

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.ungdungnghenhac.Adapter.MainViewPageAdapter
import com.example.ungdungnghenhac.Fragment.Fragment_Tim_Kiem
import com.example.ungdungnghenhac.Fragment.Fragment_Trang_Chu
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setControl()
        setEvent()
    }

    private fun setEvent() {
        val mainViewPagerAdapter = MainViewPageAdapter(supportFragmentManager)
        mainViewPagerAdapter.addFragment(Fragment_Trang_Chu(), "Trang chủ")
        mainViewPagerAdapter.addFragment(Fragment_Tim_Kiem(), "Tìm kiếm")
        viewPager!!.adapter = mainViewPagerAdapter
        tabLayout!!.setupWithViewPager(viewPager)
        tabLayout!!.getTabAt(0)?.setIcon(R.drawable.icontrangchu)
        tabLayout!!.getTabAt(1)?.setIcon(R.drawable.icontimkiem)
    }

    private fun setControl() {
        tabLayout = findViewById(R.id.myTabLayout)
        viewPager = findViewById(R.id.myViewPager)
    }
}