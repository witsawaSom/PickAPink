package com.example.myproject.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.myproject.R
import kotlinx.android.synthetic.main.layout_profile_pink.view.*


class MyViewPagerAdapter(private val mContext:Context, private val layouts: IntArray) : PagerAdapter() {

    override fun getCount(): Int {
        return layouts.size;
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(R.layout.layout_profile_pink, container, false) as ViewGroup
        layout.imageView.setImageResource(R.drawable.profile_pink_test)
        layout.nickname.text = "IU"
        container.addView(layout)
        return layout
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View);
    }
}