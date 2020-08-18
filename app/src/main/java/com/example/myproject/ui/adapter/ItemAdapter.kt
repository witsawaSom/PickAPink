package com.example.myproject.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myproject.R
import com.example.myproject.model.Item
import kotlinx.android.synthetic.main.layout_item.view.*
import kotlinx.android.synthetic.main.layout_item.view.imageView
import kotlinx.android.synthetic.main.layout_profile_pink.view.*

class ItemAdapter(private val mContext: Context, private val items: ArrayList<Item>) : RecyclerView.Adapter<ItemAdapter.ItemHolder>(){


    class ItemHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {
        fun bind(item: Item) {
            itemView.apply {
                tvName.text = item.name
                Glide.with(context)
                    .load(item.url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(RequestOptions().transform(RoundedCorners(40)))
                    .transform(CenterCrop(), RoundedCorners(24))
                    .into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemAdapter.ItemHolder(
            LayoutInflater.from(
                mContext).inflate(R.layout.layout_item, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.layoutItem.setOnClickListener {

        }
    }
}