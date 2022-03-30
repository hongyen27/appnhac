package com.example.ungdungnghenhac.Fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import com.example.ungdungnghenhac.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class DiaNhacFragment: Fragment() {
    var viewv: View? = null
    var circleImageView: CircleImageView? = null
    var objectAnimator: ObjectAnimator? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewv = inflater.inflate(R.layout.fragment_dianhac, container, false)
        circleImageView = viewv!!.findViewById(R.id.imgviewcircle)
        objectAnimator = ObjectAnimator.ofFloat(circleImageView, "rotation", 0f, 360f)
        objectAnimator!!.setDuration(10000)
        objectAnimator!!.setRepeatCount(ValueAnimator.INFINITE)
        objectAnimator!!.setRepeatMode(ValueAnimator.RESTART)
        objectAnimator!!.setInterpolator(LinearInterpolator())
        objectAnimator!!.start()
        return viewv
    }

    fun Playnhac(hinhanh: String?) {
        Picasso.with(activity).load(hinhanh).into(circleImageView)
    }
}