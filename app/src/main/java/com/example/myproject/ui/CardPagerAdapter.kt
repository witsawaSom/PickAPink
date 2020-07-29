package com.example.myproject.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.myproject.R
import com.example.myproject.model.PinkProfile


class CardPagerAdapter(private val mContext : Context,private val layouts: ArrayList<PinkProfile>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return false
    }

    override fun getCount(): Int {
        return layouts.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.layout_profile_pink, container, false)
        setData(view, position)
        container.addView(view)
        return view
    }

    private fun setData(view: View, position: Int) {
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }


}