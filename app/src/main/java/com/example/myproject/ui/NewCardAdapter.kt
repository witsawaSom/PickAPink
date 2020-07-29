package com.example.myproject.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myproject.HolderSelectedListener
import com.example.myproject.PositionSelectedListener
import com.example.myproject.R
import com.example.myproject.model.PinkProfile
import kotlinx.android.synthetic.main.layout_profile_pink.view.*

class NewCardAdapter(private val mContext: Context, private val layouts: ArrayList<PinkProfile>,private val listener: HolderSelectedListener?) : RecyclerView.Adapter<NewCardAdapter.ViewHolder>() {

    class ViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {
        fun bind(pinkProfile: PinkProfile) {
            itemView.apply {
                nickname.text = pinkProfile.name
                detail.text = pinkProfile.location
                Glide.with(context)
                    .load(pinkProfile.url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(RequestOptions().transform(RoundedCorners(40)))
                    .transform(CenterCrop(),RoundedCorners(24))
                    .into(imageView)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_profile_pink, parent, false))
    }

    override fun getItemCount(): Int {
        return layouts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(layouts[position])
        holder.itemView.layoutInfo.setOnClickListener {
            ViewCompat.setTransitionName(holder.itemView.imageView, layouts[position].name)
            listener?.onHolderPositionSelected(layouts[position],holder)
        }
    }
}