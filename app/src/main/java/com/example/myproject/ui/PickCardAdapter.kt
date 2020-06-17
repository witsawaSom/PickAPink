package com.example.myproject.ui

import android.content.ClipData.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.myproject.R
import kotlinx.android.synthetic.main.activity_pick.*
import kotlinx.android.synthetic.main.layout_profile_pink.view.*


class PickCardAdapter(private val mContext: Context, private val layouts: IntArray) :BaseAdapter(){

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        val inflater = LayoutInflater.from(mContext)
        val layout = inflater.inflate(R.layout.layout_profile_pink, parent, false) as ViewGroup
        layout.nickname.text = "IU"

        layout.command.visibility = View.GONE
        return layout
    }

    override fun getItem(position: Int): Any {
        return layouts[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return layouts.size
    }

}