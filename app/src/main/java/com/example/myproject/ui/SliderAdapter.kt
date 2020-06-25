package com.example.myproject.ui

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.myproject.R
import com.example.myproject.model.SliderItem
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter(context: Context) : SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {

    private var mSliderItems: MutableList<SliderItem> = ArrayList()

    fun renewItems(sliderItems: MutableList<SliderItem>) {
        mSliderItems = sliderItems
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(sliderItem: SliderItem) {
        mSliderItems.add(sliderItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View = LayoutInflater.from(parent.context).inflate(R.layout.image_slider_layout_item   , null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val sliderItem: SliderItem = mSliderItems[position]
        viewHolder.textViewDescription.text = sliderItem.getDescription()
        viewHolder.textViewDescription.textSize = 16f
        viewHolder.textViewDescription.setTextColor(Color.WHITE)

        Glide.with(viewHolder.itemView)
            .load(sliderItem.getImageUrl())
            .centerCrop()
            .into(viewHolder.imageViewBackground)

        viewHolder.itemView.setOnClickListener {
            Log.d("selected","This is item in position $position");
        }
    }

    override fun getCount(): Int {
        return mSliderItems.size
    }

    inner class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        var imageViewBackground: ImageView = itemView.findViewById(R.id.iv_auto_image_slider)
        var imageGifContainer: ImageView = itemView.findViewById(R.id.iv_gif_container)
        var textViewDescription: TextView = itemView.findViewById(R.id.tv_auto_image_slider)

    }


}